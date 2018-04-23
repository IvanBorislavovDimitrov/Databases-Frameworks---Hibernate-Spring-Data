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

    private final ItemsService itemService;
    private final JSONParser jsonParser;

    @Autowired
    public ItemsController(ItemsService itemService, JSONParser jsonParser) {
        this.itemService = itemService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            ItemJSONImportDTO[] items = this.jsonParser.read(ItemJSONImportDTO[].class, jsonContent);
            for (ItemJSONImportDTO item : items) {
                try {
                    this.itemService.create(item);
                    sb.append(String.format("Record %s successfully imported.", item.getName()));
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

        return sb.toString();
    }
}
