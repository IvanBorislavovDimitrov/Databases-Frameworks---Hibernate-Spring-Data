package app.controllers;

import app.domain.dtos.json.importDtos.EmployeeCardJSONDto;
import app.parsers.JsonParser;
import app.service.api.EmployeeCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeCardController {

    private final EmployeeCardService employeeCardService;
    private final JsonParser jsonParser;

    @Autowired
    public EmployeeCardController(EmployeeCardService employeeCardService, JsonParser jsonParser) {
        this.employeeCardService = employeeCardService;
        this.jsonParser = jsonParser;
    }

    public String importEmployeeCardsFromJSON(String fileContent) {
        StringBuilder sb = new StringBuilder();
        EmployeeCardJSONDto[] employeeCards = this.jsonParser.read(EmployeeCardJSONDto[].class, fileContent);
        for (EmployeeCardJSONDto employeeCard : employeeCards) {
            try {
                this.employeeCardService.create(employeeCard);
                sb.append(String.format("Successfully imported Employee Card %s.\r\n", employeeCard.getNumber()));
            } catch (IllegalArgumentException e) {
                sb.append("Error: Invalid data.\r\n");
            }
        }

        return sb.toString();
    }

    public String freeCards() {
        return this.jsonParser.write(this.employeeCardService.freeCards());
    }
}
