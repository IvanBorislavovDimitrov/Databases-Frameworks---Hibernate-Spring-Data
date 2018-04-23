package com.masdefect.controller;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.SolarSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class SolarSystemController {

    private final SolarSystemService solarSystemService;
    private final FileParser jsonParser;

    @Autowired
    public SolarSystemController(SolarSystemService solarSystemService,
                                 @Qualifier(value = "JSONParser") FileParser parser) {
        this.solarSystemService = solarSystemService;
        this.jsonParser = parser;
    }

    public String importDataFromJSON(String fileContent) {
        StringBuilder sb = new StringBuilder();
        try {
            SolarSystemImportJSONDto[] solarSystems = this.jsonParser.read(SolarSystemImportJSONDto[].class, fileContent);
            for (SolarSystemImportJSONDto solarSystem : solarSystems) {
                try {
                    this.solarSystemService.create(solarSystem);
                    sb.append(String.format("Successfully imported Solar System %s.", solarSystem.getName()));
                    sb.append(System.lineSeparator());
                } catch (IllegalArgumentException e) {
                    sb.append("Error: Invalid data.");
                    sb.append(System.lineSeparator());
                }
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
