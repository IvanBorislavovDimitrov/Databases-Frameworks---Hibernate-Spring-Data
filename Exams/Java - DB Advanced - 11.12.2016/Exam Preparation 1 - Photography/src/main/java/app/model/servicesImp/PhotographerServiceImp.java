package app.model.servicesImp;

import app.io.FileIO;
import app.model.dto.json.LandscapePhotographersExport;
import app.model.dto.json.PhotographerDto;
import app.model.dto.json.PhotographerDtoExport;
import app.model.dto.xml.photographers_with_same_camera_make.LenDtoXml;
import app.model.dto.xml.photographers_with_same_camera_make.PhotographerDtoXml;
import app.model.dto.xml.photographers_with_same_camera_make.PhotographerDtoXmlWrapper;
import app.model.entities.BasicCamera;
import app.model.entities.DSLRCamera;
import app.model.entities.Len;
import app.model.entities.Photographer;
import app.model.repositiories.BasicCameraRepository;
import app.model.repositiories.LenRepository;
import app.model.repositiories.PhotographerRepository;
import app.model.serializers.JsonSerializer;
import app.model.serializers.XmlSerializer;
import app.model.services.PhotographerService;
import app.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.util.*;

@Service
@Transactional
public class PhotographerServiceImp implements PhotographerService {

    private final PhotographerRepository photographerRepository;
    private final LenRepository lenRepository;
    private final FileIO fileIO;
    private final JsonSerializer jsonSerializer;
    private final ModelMapper mapper;
    private final BasicCameraRepository basicCameraRepository;
    private final XmlSerializer xmlSerializer;

    @Autowired
    public PhotographerServiceImp(PhotographerRepository photographerRepository,
                                  LenRepository lenRepository, FileIO fileIO, JsonSerializer jsonSerializer,
                                  BasicCameraRepository basicCameraRepository, XmlSerializer xmlSerializer) {
        this.photographerRepository = photographerRepository;
        this.lenRepository = lenRepository;
        this.fileIO = fileIO;
        this.jsonSerializer = jsonSerializer;
        this.basicCameraRepository = basicCameraRepository;
        this.xmlSerializer = xmlSerializer;
        this.mapper = new ModelMapper();
    }

    @Override
    public void sameCamerasPhotographers(String fileName) throws JAXBException {
        List<Photographer> photographers = this.photographerRepository.findAll();
        PhotographerDtoXmlWrapper photographerDtoXmlWrapper = new PhotographerDtoXmlWrapper();
        for (Photographer photographer : photographers) {
            if (photographer.getPrimaryCamera().getMake().equals(photographer.getSecondaryCamera().getMake())) {
                if (photographer.getLens().size() > 0) {
                    PhotographerDtoXml photographerDtoXml = new PhotographerDtoXml();
                    photographerDtoXml.setName(photographer.getFirstName() + " " + photographer.getLastName());
                    photographerDtoXml.setPrimaryCamera(photographer.getPrimaryCamera().getMake());
                    for (Len len : photographer.getLens()) {
                        LenDtoXml lenDtoXml = new LenDtoXml();
                        lenDtoXml.setMake(len.getMake());
                        lenDtoXml.setFocalLength(len.getFocalLength());
                        lenDtoXml.setMaxAperture(len.getMaxAperture());
                        photographerDtoXml.getLens().add(lenDtoXml);
                    }
                    photographerDtoXmlWrapper.getPhotographerDtoXmls().add(photographerDtoXml);
                }
            }
        }
        this.xmlSerializer.serialize(photographerDtoXmlWrapper, fileName);
    }

    @Override
    public void landScapePhotographers(String fileName) {
        List<Photographer> photographers = this.photographerRepository.findAll();
        List<LandscapePhotographersExport> landscapePhotographersExports = new ArrayList<>();
        for (Photographer photographer : photographers) {
            if (photographer.getPrimaryCamera() instanceof DSLRCamera) {
                if (photographer.getLens().size() != 0 &&
                        photographer.getLens().stream().filter(x -> x.getFocalLength() <= 30).count() ==
                        (long) photographer.getLens().size()) {
                    LandscapePhotographersExport landscapePhotographersExport = new LandscapePhotographersExport();
                    landscapePhotographersExport.setFirstName(photographer.getFirstName());
                    landscapePhotographersExport.setLastName(photographer.getLastName());
                    landscapePhotographersExport.setCameraMake(photographer.getPrimaryCamera().getMake());
                    landscapePhotographersExport.setLensesCount(photographer.getLens().size());
                    landscapePhotographersExports.add(landscapePhotographersExport);
                }
            }

            landscapePhotographersExports.sort(Comparator.comparing(LandscapePhotographersExport::getFirstName));
            String json = this.jsonSerializer.serialize(landscapePhotographersExports);
            this.fileIO.writeFile(json, fileName);
        }
    }

    @Override
    public void orderedCustomers(String fileName) {
        List<Photographer> photographers = this.photographerRepository.findAll();
        List<PhotographerDtoExport> photographerDtoExports = new ArrayList<>();
        for (Photographer photographer : photographers) {
            PhotographerDtoExport photographerDtoExport = this.mapper.map(photographer, PhotographerDtoExport.class);
            photographerDtoExports.add(photographerDtoExport);
        }
        photographerDtoExports.sort((x1, x2) -> {
            if (x1.getFirstName().equals(x2.getFirstName())) {
                return x1.getLastName().compareTo(x2.getLastName());
            }

            return x1.getFirstName().compareTo(x2.getFirstName());
        });
        String jsonContent = this.jsonSerializer.serialize(photographerDtoExports);
        this.fileIO.writeFile(jsonContent, fileName);
    }

    @Override
    public void importPhotographersToTheDatabase(String fileName) {
        String jsonContent = this.fileIO.readFile(fileName);
        PhotographerDto[] photographerDtos = this.jsonSerializer.deserialize(PhotographerDto[].class, jsonContent);
        for (PhotographerDto photographerDto : photographerDtos) {
            if (! ValidationUtil.isValid(photographerDto)) {
                System.out.println("Error. Invalid data provided");
                continue;
            }
            List<BasicCamera> cameras = this.basicCameraRepository.findAll();
            Photographer photographer = this.mapper.map(photographerDto, Photographer.class);
            BasicCamera primaryCamera = cameras.get(new Random().nextInt(cameras.size()));

            BasicCamera secondaryCamera = cameras.get(new Random().nextInt(cameras.size()));

            photographer.setPrimaryCamera(primaryCamera);

            photographer.setSecondaryCamera(secondaryCamera);
            primaryCamera.getPrimaryCameraPhotographers().add(photographer);
            secondaryCamera.getSecondaryCameraPhotographers().add(photographer);

            for (int lenId : photographerDto.getLenses()) {
                Len len = this.lenRepository
                        .findFirstById((long) lenId);
                if (len != null) {
                    photographer.getLens().add(len);
                }
            }

            this.photographerRepository.save(photographer);
            System.out.println(String.format("Successfully imported %s %s | Lenses: %d",
                    photographer.getFirstName(), photographer.getLastName(), photographer.getLens().size()));

            for (int lenId : photographerDto.getLenses()) {
                Len len = this.lenRepository
                        .findFirstById((long) lenId);
                if (len != null) {
                    len.setPhotographer(photographer);
                    this.lenRepository.save(len);
                }
            }
        }
    }
}
