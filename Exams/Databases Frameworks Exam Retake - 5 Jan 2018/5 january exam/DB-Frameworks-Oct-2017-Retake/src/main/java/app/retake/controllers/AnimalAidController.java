package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnimalAidController {

    private final AnimalAidService animalAidService;
    private final Parser parser;

    @Autowired
    public AnimalAidController(AnimalAidService animalAidService, @Qualifier(value = "JSONParser") Parser parser) {
        this.animalAidService = animalAidService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            AnimalAidJSONImportDTO[] animalsDtos = this.parser.read(AnimalAidJSONImportDTO[].class, jsonContent);
            for (AnimalAidJSONImportDTO animalDto : animalsDtos) {
                try {
                    this.animalAidService.create(animalDto);
                    sb.append(String.format("Record %s successfully imported.", animalDto.getName()));
                    sb.append(System.lineSeparator());
                } catch (IllegalArgumentException e) {
                    sb.append("Error: Invalid data.");
                    sb.append(System.lineSeparator());
                }
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
