package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.json.exportDtos.RacingTownExportDtoJSON;
import org.softuni.mostwanted.entities.dto.json.importDtos.TownImportDtoJSON;
import org.softuni.mostwanted.parser.JSONParser;
import org.softuni.mostwanted.services.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller
public class TownController {

    private final TownService townService;
    private final JSONParser jsonParser;

    @Autowired
    TownController(TownService townService, JSONParser jsonParser) {
        this.townService = townService;
        this.jsonParser = jsonParser;
    }

    public String importFromJson(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            TownImportDtoJSON[] townDTOS = this.jsonParser.read(TownImportDtoJSON[].class, jsonContent);
            for (TownImportDtoJSON townDto : townDTOS) {
                try {
                    this.townService.create(townDto);
                    sb.append(String.format("Successfully imported Town - %s.\r\n", townDto.getName()));
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

    public String racingTownsJSONFormat() {
        List<RacingTownExportDtoJSON> racingTowns = this.townService.getRacingTowns();
        try {
            return this.jsonParser.write(racingTowns);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
