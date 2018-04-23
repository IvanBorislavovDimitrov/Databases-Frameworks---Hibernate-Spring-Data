package app.exam.controller;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.parser.JSONParser;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ItemsController {

    private final JSONParser jsonParser;
    private final ItemsService itemsService;

    @Autowired
    public ItemsController(JSONParser jsonParser, ItemsService itemsService) {
        this.jsonParser = jsonParser;
        this.itemsService = itemsService;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        ItemJSONImportDTO[] itemJSONImportDTOS = new ItemJSONImportDTO[0];

        try {
            itemJSONImportDTOS = this.jsonParser.read(ItemJSONImportDTO[].class, jsonContent);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }

        for (ItemJSONImportDTO itemJSONImportDTO : itemJSONImportDTOS) {
            try {
                this.itemsService.create(itemJSONImportDTO);
                sb.append(String.format("Record %s successfully imported.", itemJSONImportDTO.getName()));
                sb.append(System.lineSeparator());
            } catch (IllegalArgumentException e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString();
    }
}
