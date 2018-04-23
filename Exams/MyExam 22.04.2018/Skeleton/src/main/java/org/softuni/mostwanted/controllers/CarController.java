package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.json.importDtos.CarImportDtoJSON;
import org.softuni.mostwanted.parser.JSONParser;
import org.softuni.mostwanted.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class CarController {

    private final CarService carService;
    private final JSONParser jsonParser;

    @Autowired
    public CarController(CarService carService, JSONParser jsonParser) {
        this.carService = carService;
        this.jsonParser = jsonParser;
    }

    public String importFromJson(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            CarImportDtoJSON[] carDTOS = this.jsonParser.read(CarImportDtoJSON[].class, jsonContent);
            for (CarImportDtoJSON carDto : carDTOS) {
                try {
                    this.carService.create(carDto);
                    sb.append(String.format("Successfully imported Car - %s %s @ %d.\r\n",
                            carDto.getBrand(), carDto.getModel(), carDto.getYearOfProduction()));
                } catch (IllegalArgumentException e) {
                    sb.append(e.getMessage());
                    sb.append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

}
