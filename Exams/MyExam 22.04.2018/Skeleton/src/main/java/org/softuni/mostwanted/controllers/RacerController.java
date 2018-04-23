package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.json.exportDtos.RacingCarsExportDtoJSON;
import org.softuni.mostwanted.entities.dto.json.importDtos.RacerImportDtoJSON;
import org.softuni.mostwanted.entities.dto.xml.exportDtos.MostWantedExportDtoXML;
import org.softuni.mostwanted.parser.JSONParser;
import org.softuni.mostwanted.parser.XMLParser;
import org.softuni.mostwanted.services.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class RacerController {

    private final RacerService racerService;
    private final JSONParser jsonParser;
    private final XMLParser xmlParser;

    @Autowired
    public RacerController(RacerService racerService, JSONParser jsonParser, XMLParser xmlParser) {
        this.racerService = racerService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    public String mostWanted() {
        MostWantedExportDtoXML mostWantedExportDtoXML = this.racerService.getMostWanted();
        try {
            return xmlParser.write(mostWantedExportDtoXML);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String racingCars() {
        List<RacingCarsExportDtoJSON> racingCars = this.racerService.racingCars();
        try {
            return this.jsonParser.write(racingCars);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String importFromJson(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            RacerImportDtoJSON[] racerDTOS = this.jsonParser.read(RacerImportDtoJSON[].class, jsonContent);
            for (RacerImportDtoJSON racerDto : racerDTOS) {
                try {
                    this.racerService.create(racerDto);
                    sb.append(String.format("Successfully imported Racer - %s.\r\n", racerDto.getName()));
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
