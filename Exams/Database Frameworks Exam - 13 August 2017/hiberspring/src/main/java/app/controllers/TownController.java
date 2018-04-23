package app.controllers;

import app.domain.dtos.json.importDtos.TownImportJSONDto;
import app.domain.dtos.xml.exportDtos.TownWrapperExportXml;
import app.parsers.JsonParser;
import app.parsers.XmlParser;
import app.service.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TownController {

    private final TownService townService;
    private final JsonParser jsonParser;
    private final XmlParser xmlParser;

    @Autowired
    public TownController(TownService townService, JsonParser jsonParser, XmlParser xmlParser) {
        this.townService = townService;
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    public String importTownsFromJSON(String fileContent) {
        StringBuilder sb = new StringBuilder();
        TownImportJSONDto[] towns = this.jsonParser.read(TownImportJSONDto[].class, fileContent);

        for (TownImportJSONDto town : towns) {
            try {
                this.townService.create(town);
                sb.append(String.format("Town %s has been added.\r\n", town.getName()));
            } catch (IllegalArgumentException e) {
                sb.append("Error: Invalid data.\r\n");
            }
        }

        return sb.toString();
    }

    public String getTownsInfo() {
        TownWrapperExportXml townWrapperExportXml = new TownWrapperExportXml();
        townWrapperExportXml.setTowns(this.townService.getTownsInfo());
        return this.xmlParser.write(townWrapperExportXml);
    }
}
