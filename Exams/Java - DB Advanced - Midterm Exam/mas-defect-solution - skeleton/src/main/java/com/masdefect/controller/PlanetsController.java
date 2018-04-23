package com.masdefect.controller;

import com.masdefect.domain.dto.json.PlanetExportJSONDto;
import com.masdefect.domain.dto.json.PlanetImportJSONDto;
import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.parser.JSONParser;
import com.masdefect.service.PlanetService;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class PlanetsController {

    private JSONParser jsonParser;
    private PlanetService planetService;

    public PlanetsController(JSONParser jsonParser, PlanetService planetService) {
        this.jsonParser = jsonParser;
        this.planetService = planetService;
    }

    public String importDataFromJSON(String fileContent){
        StringBuilder sb = new StringBuilder();
        try {
            PlanetImportJSONDto[] planetImportJSONDtos = this.jsonParser.read(PlanetImportJSONDto[].class, fileContent);
            for (PlanetImportJSONDto planetImportJSONDto : planetImportJSONDtos) {
                try {
                    this.planetService.create(planetImportJSONDto);
                    sb.append(String.format("Successfully imported Solar Planet %s.", planetImportJSONDto.getName()));
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

    public String planetsWithNoPeopleTeleportedToThem(){
        try {
            return this.jsonParser.write(this.planetService.findAllPlanetsWithoutPeopleTeleportedFromThem(), null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
