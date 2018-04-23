package app.model.servicesImp;

import app.model.dto.xml.ParticipantDto;
import app.model.dto.xml.WorkshopDto;
import app.model.dto.xml.WorkshopWrapper;
import app.model.dto.xml.workshops_by_location.LocationDtoXml;
import app.model.dto.xml.workshops_by_location.LocationDtoWrapperXml;
import app.model.dto.xml.workshops_by_location.WorkshopDtoXml;
import app.model.entities.Photographer;
import app.model.entities.Workshop;
import app.model.repositiories.PhotographerRepository;
import app.model.repositiories.WorkshopRepository;
import app.model.serializers.XmlSerializer;
import app.model.services.WorkshopService;
import app.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkshopServiceImp implements WorkshopService {

    private final WorkshopRepository workshopRepository;
    private final XmlSerializer xmlSerializer;
    private final ModelMapper mapper;
    private final PhotographerRepository photographerRepository;

    @Autowired
    public WorkshopServiceImp(WorkshopRepository workshopRepository, XmlSerializer xmlSerializer, PhotographerRepository photographerRepository) {
        this.workshopRepository = workshopRepository;
        this.xmlSerializer = xmlSerializer;
        this.photographerRepository = photographerRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void workshopsByLocations(String fileName) throws JAXBException {
        LocationDtoWrapperXml locationDtoWrapper = new LocationDtoWrapperXml();
        List<Workshop> workshops = this.workshopRepository.findAll();
        this.addLocations(locationDtoWrapper, workshops);

        for (LocationDtoXml locationDto : locationDtoWrapper.getLocationDtos()) {
            List<Workshop> workshopsWithThisLocation = workshops
                    .stream()
                    .filter(x -> x.getLocation().equals(locationDto.getName()) &&
                                x.getParticipants().size() > 5)
                    .collect(Collectors.toList());
            for (Workshop workshop : workshopsWithThisLocation) {
                WorkshopDtoXml workshopDtoXml = new WorkshopDtoXml();
                workshopDtoXml.setName(workshop.getName());
                BigDecimal price = workshop.getPricePerParticipant()
                        .multiply(BigDecimal.valueOf(workshop.getParticipants().size()));
                price = price.subtract(price.multiply(BigDecimal.valueOf(0.2)));
                workshopDtoXml.setTotalProfit(price);
                for (Photographer participant : workshop.getParticipants()) {
                    workshopDtoXml.getParticipantDtoWrapper().getParticipantDtosa()
                            .add(participant.getFirstName() + " " + participant.getLastName());
                }
                locationDto.getWorkshopDtos().add(workshopDtoXml);
            }
        }

        this.xmlSerializer.serialize(locationDtoWrapper, fileName);
    }

    private void addLocations(LocationDtoWrapperXml locationDtoWrapper, List<Workshop> workshops) {
        for (Workshop workshop : workshops) {
            if (workshop.getParticipants().size() > 5) {
                if (!this.containsLocation(locationDtoWrapper, workshop.getLocation())) {
                    LocationDtoXml locationDto = new LocationDtoXml();
                    locationDto.setName(workshop.getLocation());
                    locationDtoWrapper.getLocationDtos().add(locationDto);
                }
            }
        }
    }

    private boolean containsLocation(LocationDtoWrapperXml locationDtoWrapper, String location) {
        for (LocationDtoXml locationDto : locationDtoWrapper.getLocationDtos()) {
            if (location.equals(locationDto.getName())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void importWorkshopsInTheDatabase(String fileName) throws JAXBException, ParseException {
        WorkshopWrapper workshopWrapper = this.xmlSerializer.deserialize(WorkshopWrapper.class, fileName);
        for (WorkshopDto workshopDto : workshopWrapper.getWorkshops()) {
            Workshop workshop = new Workshop();
            if (! ValidationUtil.isValid(workshopDto)) {
                System.out.println("Error. Invalid data provided");
                continue;
            }
            workshop.setPricePerParticipant(workshopDto.getPrice());
            workshop.setName(workshopDto.getName());
            workshop.setStartDate(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(workshopDto.getStartDate()));
            workshop.setEndDate(new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").parse(workshopDto.getEndDate()));
            workshop.setLocation(workshopDto.getLocation());

            String trainerFirstName = workshopDto.getTrainer().split("\\s+")[0];
            String trainerLastName = workshopDto.getTrainer().split("\\s+")[1];
            Photographer trainer = this.photographerRepository.findFirstByFirstNameAndLastName(trainerFirstName, trainerLastName);
            for (ParticipantDto participantDto : workshopDto.getParticipantDtoWrapper().getParticipantDtos()) {
                Photographer participant = this.photographerRepository
                        .findFirstByFirstNameAndLastName(participantDto.getFirstName(), participantDto.getLastName());
                workshop.getParticipants().add(participant);
            }
            workshop.setTrainer(trainer);
            if (ValidationUtil.isValid(workshop)) {
                this.workshopRepository.save(workshop);
                System.out.println(String.format("Successfully imported %s", workshop.getName()));
            } else {
                System.out.println("Error. Invalid data provided");
            }
        }
    }
}
