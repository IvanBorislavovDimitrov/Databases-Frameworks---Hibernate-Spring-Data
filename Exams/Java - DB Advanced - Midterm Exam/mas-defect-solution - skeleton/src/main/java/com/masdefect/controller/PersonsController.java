package com.masdefect.controller;

import com.masdefect.domain.dto.json.PersonImportJSONDto;
import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.parser.JSONParser;
import com.masdefect.service.PersonService;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class PersonsController {

    private PersonService personService;
    private JSONParser jsonParser;

    public PersonsController(PersonService personService, JSONParser jsonParser) {
        this.personService = personService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String fileContent){
        StringBuilder sb = new StringBuilder();
        try {
            PersonImportJSONDto[] personImportJSONDtos = this.jsonParser.read(PersonImportJSONDto[].class, fileContent);
            for (PersonImportJSONDto personImportJSONDto : personImportJSONDtos) {
                try {
                    this.personService.create(personImportJSONDto);
                    sb.append(String.format("Successfully imported Solar Person %s.", personImportJSONDto.getName()));
                    sb.append(System.lineSeparator());
                } catch (IllegalArgumentException e) {
                    sb.append("Error: Invalid data.");
                    sb.append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
