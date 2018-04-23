package app.exam.controller;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;

import app.exam.parser.JSONParser;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeesController {

    private final JSONParser jsonParser;
    private final EmployeeService employeeService;

    @Autowired
    public EmployeesController(JSONParser jsonParser, EmployeeService employeeService) {
        this.jsonParser = jsonParser;
        this.employeeService = employeeService;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            EmployeeJSONImportDTO[] employeeJSONImportDTOS = this.jsonParser.read(EmployeeJSONImportDTO[].class, jsonContent);
            for (EmployeeJSONImportDTO employeeJSONImportDTO : employeeJSONImportDTOS) {
                try {
                    this.employeeService.create(employeeJSONImportDTO);
                } catch (IllegalArgumentException exc) {
                    sb.append("Error: Invalid data.");
                    sb.append(System.lineSeparator());
                    continue;
                }
                sb.append(String.format("Record %s successfully imported.", employeeJSONImportDTO.getName()));
                sb.append(System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
