package org.softuni.mostwanted.services_imp;

import org.softuni.mostwanted.entities.Car;
import org.softuni.mostwanted.entities.Racer;
import org.softuni.mostwanted.entities.dto.json.importDtos.CarImportDtoJSON;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.services.CarService;
import org.softuni.mostwanted.services.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CarServiceImp implements CarService {

    private final CarRepository carRepository;
    private final RacerService racerService;
    private final ModelParser modelParser;

    @Autowired
    public CarServiceImp(CarRepository carRepository, RacerService racerService, ModelParser modelParser) {
        this.carRepository = carRepository;
        this.racerService = racerService;
        this.modelParser = modelParser;
    }

    public void create(CarImportDtoJSON carDto) {
        if (!ValidationUtil.isValid(carDto)) {
            throw new IllegalArgumentException("Error: Incorrect Data!");
        }

        Racer racer = this.racerService.findByName(carDto.getRacerName());

        if (racer == null) {
            throw new IllegalArgumentException("Error: Incorrect Data!");
        }

        for (Car carInData : this.carRepository.findAll()) {
            if (carInData.getBrand().equals(carDto.getBrand()) && carInData.getModel().equals(carDto.getModel()) &&
                    carInData.getYearOfProduction().equals(carDto.getYearOfProduction())) {
                throw new IllegalArgumentException("Error: Duplicate Data");
            }
        }

        Car car = this.modelParser.convert(carDto, Car.class);
        car.setRacer(racer);

        this.carRepository.save(car);
    }

    @Override
    public Car findById(Integer id) {
        return this.carRepository.findOne(id);
    }

    @Override
    public List<Car> findAll() {
        return this.carRepository.findAll();
    }


}
