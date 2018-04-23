package com.masdefect.controller;

import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.json.AnomalyVictimsJSONDto;
import com.masdefect.parser.JSONParser;
import com.masdefect.service.AnomalyService;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class AnomalyVictimsController {

    private JSONParser jsonParser;
    private AnomalyService anomalyService;

    public AnomalyVictimsController(JSONParser jsonParser, AnomalyService anomalyService) {
        this.jsonParser = jsonParser;
        this.anomalyService = anomalyService;
    }

    public String importDataFromJSON(String fileContent){
        StringBuilder sb = new StringBuilder();
        try {
            AnomalyVictimsJSONDto[] anomalyVictimsJSONDtos = this.jsonParser.read(AnomalyVictimsJSONDto[].class, fileContent);
            for (AnomalyVictimsJSONDto anomalyVictimsJSONDto : anomalyVictimsJSONDtos) {
                try {
                    this.anomalyService.create(anomalyVictimsJSONDto);
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
}
