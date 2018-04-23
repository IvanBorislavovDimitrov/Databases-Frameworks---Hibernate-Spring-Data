package app.model.servicesImp;

import app.io.FileIO;
import app.model.dto.json.CameraDto;
import app.model.entities.DSLRCamera;
import app.model.entities.MirrorlessCamera;
import app.model.repositiories.BasicCameraRepository;
import app.model.serializers.JsonSerializer;
import app.model.services.BasicCameraService;
import app.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BasicCameraServiceImp implements BasicCameraService {

    private static final String DSLR_CAMERA = "DSLR";
    private static final String MIRRORLESS_CAMERA = "Mirrorless";

    private final BasicCameraRepository basicCameraRepository;
    private final FileIO fileIO;
    private final JsonSerializer jsonSerializer;
    private final ModelMapper mapper;

    @Autowired
    public BasicCameraServiceImp(BasicCameraRepository basicCameraRepository, FileIO fileIO, JsonSerializer jsonSerializer) {
        this.basicCameraRepository = basicCameraRepository;
        this.fileIO = fileIO;
        this.jsonSerializer = jsonSerializer;
        this.mapper = new ModelMapper();
    }

    @Override
    public void importCamerasInTheDatabase(String fileName) {
        String jsonContent = this.fileIO.readFile(fileName);
        CameraDto[] cameraDtos = this.jsonSerializer.deserialize(CameraDto[].class, jsonContent);
        for (CameraDto cameraDto : cameraDtos) {
            if (cameraDto.getType() == null) {
                System.out.println("Error: Invalid data provided");
                continue;
            }
            if (cameraDto.getType().equals(DSLR_CAMERA)) {
                DSLRCamera camera = this.mapper.map(cameraDto, DSLRCamera.class);
                if (! ValidationUtil.isValid(camera)) {
                    System.out.println("Error: Invalid data provided");
                    continue;
                }
                this.basicCameraRepository.save(camera);
                System.out.println(String.format("Successfully imported %s %s %dD", camera.getMake(),
                        camera.getModel(), camera.getMinIso()));
            } else if (cameraDto.getType().equals(MIRRORLESS_CAMERA)) {
                MirrorlessCamera camera = this.mapper.map(cameraDto, MirrorlessCamera.class);
                if (! ValidationUtil.isValid(camera)) {
                    System.out.println("Error: Invalid data provided");
                    continue;
                }
                this.basicCameraRepository.save(camera);
                System.out.println(String.format("Successfully imported %s %s %dD", camera.getMake(),
                        camera.getModel(), camera.getMinIso()));
            }
        }
    }
}
