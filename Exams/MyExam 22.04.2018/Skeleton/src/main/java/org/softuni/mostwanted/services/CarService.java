package org.softuni.mostwanted.services;

import org.softuni.mostwanted.entities.Car;
import org.softuni.mostwanted.entities.dto.json.importDtos.CarImportDtoJSON;

import java.util.List;

public interface CarService {

    void create(CarImportDtoJSON carDto);

    Car findById(Integer id);

    List<Car> findAll();
}
