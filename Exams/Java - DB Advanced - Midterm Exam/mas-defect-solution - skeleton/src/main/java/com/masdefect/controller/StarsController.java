package com.masdefect.controller;

import com.masdefect.domain.dto.json.SolarSystemImportJSONDto;
import com.masdefect.domain.dto.json.StarImportJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.StarService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class StarsController {

    private final FileParser jsonParser;
    private final StarService starService;

    public StarsController(@Qualifier(value = "JSONParser") FileParser jsonParser, StarService starService) {
        this.jsonParser = jsonParser;
        this.starService = starService;
    }

    public String importDataFromJSON(String fileContent){
        StringBuilder sb = new StringBuilder();
        try {
            StarImportJSONDto[] starImportJSONDtos = this.jsonParser.read(StarImportJSONDto[].class, fileContent);
            for (StarImportJSONDto starImportJSONDto : starImportJSONDtos) {
                try {
                    this.starService.create(starImportJSONDto);
                    sb.append(String.format("Successfully imported Star %s.", starImportJSONDto.getName()));
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
