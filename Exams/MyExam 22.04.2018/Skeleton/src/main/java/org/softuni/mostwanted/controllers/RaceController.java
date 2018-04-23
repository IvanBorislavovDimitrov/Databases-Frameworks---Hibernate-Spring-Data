package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.xml.importDtos.RaceImportDtoXML;
import org.softuni.mostwanted.entities.dto.xml.importDtos.RaceWrapperImportDtoXML;
import org.softuni.mostwanted.parser.XMLParser;
import org.softuni.mostwanted.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceController {

    private final RaceService raceService;
    private final XMLParser xmlParser;

    @Autowired
    public RaceController(RaceService raceService, XMLParser xmlParser) {
        this.raceService = raceService;
        this.xmlParser = xmlParser;
    }

    public String importFromJson(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            RaceWrapperImportDtoXML raceWrapperImportDtoXML = this.xmlParser.read(RaceWrapperImportDtoXML.class, jsonContent);
            for (RaceImportDtoXML raceEntryDto : raceWrapperImportDtoXML.getRaces()) {
                try {
                    Integer id = this.raceService.create(raceEntryDto);
                    sb.append(String.format("Successfully imported Race - %d.\r\n", id));
                } catch (IllegalArgumentException e) {
                    sb.append(e.getMessage());
                    sb.append(System.lineSeparator());
                }
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
