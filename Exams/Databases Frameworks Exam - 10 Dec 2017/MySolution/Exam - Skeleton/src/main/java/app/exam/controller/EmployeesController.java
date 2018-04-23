package app.exam.controller;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.parser.JSONParser;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class EmployeesController {

    private final EmployeeService employeeService;
    private final JSONParser jsonParser;

    @Autowired
    public EmployeesController(EmployeeService employeeService, JSONParser jsonParser) {
        this.employeeService = employeeService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            EmployeeJSONImportDTO[] employeeJSONImportDTOS = this.jsonParser.read(EmployeeJSONImportDTO[].class, jsonContent);
            for (EmployeeJSONImportDTO employeeJSONImportDTO : employeeJSONImportDTOS) {
                try {
                    this.employeeService.create(employeeJSONImportDTO);
                    sb.append(String.format("Record %s successfully imported.", employeeJSONImportDTO.getName()));
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
