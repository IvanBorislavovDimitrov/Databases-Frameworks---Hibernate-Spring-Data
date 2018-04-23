package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.entities.dto.xml.importDtos.RaceEntryImportDtoXML;
import org.softuni.mostwanted.entities.dto.xml.importDtos.RaceEntryWrapperImportDtoXML;
import org.softuni.mostwanted.parser.XMLParser;
import org.softuni.mostwanted.services.RacerEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceEntryController {

    private final RacerEntryService racerEntryService;
    private final XMLParser xmlParser;

    @Autowired
    public RaceEntryController(RacerEntryService racerEntryService, XMLParser xmlParser) {
        this.racerEntryService = racerEntryService;
        this.xmlParser = xmlParser;
    }

    public String importFromJson(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            RaceEntryWrapperImportDtoXML raceEntryDTOS = this.xmlParser.read(RaceEntryWrapperImportDtoXML.class, jsonContent);
            for (RaceEntryImportDtoXML raceEntryDto : raceEntryDTOS.getRaceEntries()) {
                try {
                    Integer id = this.racerEntryService.create(raceEntryDto);
                    sb.append(String.format("Successfully imported RaceEntry - %d.\r\n", id));
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
