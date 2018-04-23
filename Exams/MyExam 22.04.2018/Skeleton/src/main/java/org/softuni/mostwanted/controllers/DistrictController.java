package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.json.importDtos.DistrictImportDtoJSON;
import org.softuni.mostwanted.parser.JSONParser;
import org.softuni.mostwanted.services.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class DistrictController {

    private final DistrictService districtService;
    private final JSONParser jsonParser;

    @Autowired
    public DistrictController(DistrictService districtService, JSONParser jsonParser) {
        this.districtService = districtService;
        this.jsonParser = jsonParser;
    }

    public String importFromJson(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            DistrictImportDtoJSON[] districtDTOS = this.jsonParser.read(DistrictImportDtoJSON[].class, jsonContent);
            for (DistrictImportDtoJSON districtDTO : districtDTOS) {
                try {
                    this.districtService.create(districtDTO);
                    sb.append(String.format("Successfully imported District wn - %s.\r\n", districtDTO.getName()));
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
