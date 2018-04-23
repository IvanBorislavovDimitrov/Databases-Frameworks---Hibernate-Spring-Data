package com.masdefect.controller;

import com.masdefect.domain.dto.json.AnomalyExportJSONDto;
import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.xml.AnomaliesXMLDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.domain.entities.Anomaly;
import com.masdefect.parser.JSONParser;
import com.masdefect.parser.XMLParser;
import com.masdefect.service.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class AnomalyController {

    private final JSONParser jsonParser;
    private final AnomalyService anomalyService;
    private final XMLParser xmlParser;

    @Autowired
    public AnomalyController(JSONParser jsonParser, AnomalyService anomalyService, XMLParser xmlParser) {
        this.jsonParser = jsonParser;
        this.anomalyService = anomalyService;
        this.xmlParser = xmlParser;
    }

    public String importDataFromJSON(String fileContent) {
        StringBuilder sb = new StringBuilder();
        try {
            AnomalyImportJSONDto[] anomalyImportJSONDtos = this.jsonParser.read(AnomalyImportJSONDto[].class, fileContent);
            for (AnomalyImportJSONDto anomalyImportJSONDto : anomalyImportJSONDtos) {
                try {
                    this.anomalyService.create(anomalyImportJSONDto);
                    sb.append(String.format("Successfully imported Anomaly."));
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

    public String importDataFromXML(String fileContent) {
        StringBuilder sb = new StringBuilder();
        try {
            AnomaliesXMLDto anomaliesXMLDto = this.xmlParser.read(AnomaliesXMLDto.class, fileContent);
            for (AnomalyXMLDto anomalyXMLDto : anomaliesXMLDto.getAnomalies()) {
                try {
                    this.anomalyService.create(anomalyXMLDto);
                    sb.append(String.format("Successfully imported Anomaly."));
                    sb.append(System.lineSeparator());
                } catch (IllegalArgumentException e) {
                    sb.append("Error: Invalid data.");
                    sb.append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String findAnomalyWithMostVictims() {
        AnomalyExportJSONDto anomaly = this.anomalyService.findMostAffectingAnomalies();
        try {
            return this.jsonParser.write(anomaly, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String exportAnomaliesOrdered() {
        AnomaliesXMLDto anomaliesXMLDto = this.anomalyService.finaAllAnomalies();
        try {
            return this.xmlParser.write(anomaliesXMLDto, null);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
