package app.retake.controllers;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.parser.JSONParser;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.text.ParseException;

@Controller
public class AnimalController {

    private final AnimalService animalService;
    private final JSONParser jsonParser;

    @Autowired
    public AnimalController(AnimalService animalService, JSONParser jsonParser) {
        this.animalService = animalService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();

        try {
            AnimalJSONImportDTO[] animalsDtos = this.jsonParser.read(AnimalJSONImportDTO[].class, jsonContent);
            for (AnimalJSONImportDTO animalDto : animalsDtos) {
                try {
                    this.animalService.create(animalDto);
                    sb.append(String.format("Record %s successfully imported.", animalDto.getName()));
                    sb.append(System.lineSeparator());
                } catch (IllegalArgumentException e) {
                    sb.append("Error: Invalid data.");
                    sb.append(System.lineSeparator());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        try {
            return this.jsonParser.write(this.animalService.findByOwnerPhoneNumber(phoneNumber));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
