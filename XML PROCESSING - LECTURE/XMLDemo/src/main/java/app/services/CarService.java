package app.services;

import app.entities.Car;
import app.entities.Part;
import app.entities.dtos.dtos_cars.CarsShortInfoDto;
import app.entities.dtos.dtos_cars.CarShortInfoDto;
import app.entities.dtos.visible_dtos.PartDto;
import app.entities.dtos.visible_dtos.Parts;
import app.repositories.CarRepo;
import app.repositories.PartRepo;
import app.serializers.XmlSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarService {

    private final CarRepo carRepo;
    private final PartRepo partRepo;
    private final ModelMapper mapper;
    private final XmlSerializer xmlSerializer;

    @Autowired
    public CarService(CarRepo carRepo, PartRepo partRepo, XmlSerializer xmlSerializer) {
        this.carRepo = carRepo;
        this.partRepo = partRepo;
        this.xmlSerializer = xmlSerializer;
        this.mapper = new ModelMapper();
    }

    public void exportCars(String fileName) {
        List<Car> cars = this.carRepo.findAll();
        List<CarShortInfoDto> carsShortInfo = new ArrayList<>();
        for (Car car : cars) {
            CarShortInfoDto carShortInfoDto = this.mapper.map(car, CarShortInfoDto.class);
            carsShortInfo.add(carShortInfoDto);
            carShortInfoDto.setParts(new Parts());
            for (Part part : car.getParts()) {
                PartDto partDto = new PartDto();
                partDto.setName(part.getName());
                partDto.setPrice(part.getPrice());
                partDto.setQuantity(part.getQuantity());
                carShortInfoDto.getParts().getPartDtos().add(partDto);
            }
        }
        CarsShortInfoDto carsShortInfoDto = new CarsShortInfoDto();
        carsShortInfoDto.setCarShortInfoDtos(carsShortInfo);
        String spot = "";
        try {
            this.xmlSerializer.exportToFile(carsShortInfoDto, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
