package app.services;

import app.entitites.Car;
import app.entitites.Part;
import app.entitites.dtos.queries.cars_with_their_list_of_parts.CarInfoAndItsPartsDto;
import app.entitites.dtos.queries.cars_from_make_toyota.CarInfoDto;
import app.entitites.dtos.import_dtos.cars.CarDtoImport;
import app.entitites.dtos.import_dtos.cars.CarWrapperImport;
import app.entitites.dtos.queries.cars_from_make_toyota.CarInfoWrapperDto;
import app.entitites.dtos.queries.cars_with_their_list_of_parts.CarInfoAndItsPartsWrapperDto;
import app.repositories.CarRepo;
import app.repositories.PartRepo;
import app.serializers.XmlSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class CarService {

    private final CarRepo carRepo;
    private final PartRepo partRepo;
    private final XmlSerializer xmlSerializer;
    private final ModelMapper mapper;

    @Autowired
    public CarService(CarRepo carRepo, PartRepo partRepo, XmlSerializer xmlSerializer) {
        this.carRepo = carRepo;
        this.partRepo = partRepo;
        this.xmlSerializer = xmlSerializer;
        this.mapper = new ModelMapper();
    }

    public void importCars(String fileName) throws JAXBException {
        CarWrapperImport cars = this.xmlSerializer.deserialize(CarWrapperImport.class, fileName);
        List<Part> parts = this.partRepo.findAll();
        Random random = new Random();
        for (CarDtoImport carDtoImport : cars.getCarDtoImports()) {
            Car car = this.mapper.map(carDtoImport, Car.class);
            for (int i = 0; i < random.nextInt(11) + 10; i++) {
                int partId = random.nextInt(parts.size());
                car.getParts().add(parts.get(partId));
                parts.get(partId).getCars().add(car); // works without this row, but let it stay for readability
            }
            this.carRepo.save(car);
        }
    }

    public void carsFromMakeToyota(String fileName) {
        List<Car> toyotaCars = this.carRepo.findAllByMake("Toyota");
        List<CarInfoDto> carInfoDtos = new ArrayList<>();
        for (Car car : toyotaCars) {
            carInfoDtos.add(this.mapper.map(car, CarInfoDto.class));
        }

        carInfoDtos.sort((x1, x2) -> {
            if (x1.getModel().equals(x2.getModel())) {
                return Double.compare(x2.getTravelledDistance(), x1.getTravelledDistance());
            }

            return x1.getModel().compareTo(x2.getModel());
        });

        try {
            CarInfoWrapperDto carInfoWrapperDto = new CarInfoWrapperDto();
            carInfoWrapperDto.setCarInfoDtos(carInfoDtos);
            this.xmlSerializer.serialize(carInfoWrapperDto, fileName);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    public void carsWithTheirListOfParts(String fileName) {
        List<Car> cars = this.carRepo.findAll();
        List<CarInfoAndItsPartsDto> carsInfo = new ArrayList<>();
        for (Car car : cars) {
            CarInfoAndItsPartsDto carInfo = this.mapper.map(car, CarInfoAndItsPartsDto.class);
            carsInfo.add(carInfo);
        }

        try {
            CarInfoAndItsPartsWrapperDto car = new CarInfoAndItsPartsWrapperDto();
            car.setCars(carsInfo);
            this.xmlSerializer.serialize(car, fileName);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
