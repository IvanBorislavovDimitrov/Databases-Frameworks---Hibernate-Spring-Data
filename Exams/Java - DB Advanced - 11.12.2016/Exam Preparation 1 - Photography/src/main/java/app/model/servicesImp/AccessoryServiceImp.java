package app.model.servicesImp;

import app.io.FileIO;
import app.model.dto.xml.AccessoryDto;
import app.model.dto.xml.AccessoryDtoWrapper;
import app.model.entities.Accessory;
import app.model.repositiories.AccessoryRepository;
import app.model.repositiories.PhotographerRepository;
import app.model.serializers.XmlSerializer;
import app.model.services.AccessoryService;
import app.model.services.PhotographerService;
import app.validation.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.Random;

@Service
public class AccessoryServiceImp implements AccessoryService {

    private final AccessoryRepository accessoryRepository;
    private final FileIO fileIO;
    private final XmlSerializer xmlSerializer;
    private final ModelMapper mapper;
    private final PhotographerRepository photographerService;

    @Autowired
    public AccessoryServiceImp(AccessoryRepository accessoryRepository, FileIO fileIO,
                               XmlSerializer xmlSerializer, PhotographerRepository photographerService) {
        this.accessoryRepository = accessoryRepository;
        this.fileIO = fileIO;
        this.xmlSerializer = xmlSerializer;
        this.photographerService = photographerService;
        this.mapper = new ModelMapper();
    }

    @Override
    public void importAccessoriesToTheDatabase(String fileName) throws JAXBException {
        AccessoryDtoWrapper accessoryDtoWrapper = this.xmlSerializer.deserialize(AccessoryDtoWrapper.class, fileName);
        for (AccessoryDto accessoryDto : accessoryDtoWrapper.getAccessoryDtos()) {
            Accessory accessory = this.mapper.map(accessoryDto, Accessory.class);
            if (ValidationUtil.isValid(accessory)) {
                accessory.setOwner(this.photographerService
                        .findOne((long)new Random().nextInt((int) this.photographerService.count())));
                this.accessoryRepository.save(accessory);
                System.out.println(String.format("Successfully imported %s", accessory.getName()));
            }
        }

    }
}
