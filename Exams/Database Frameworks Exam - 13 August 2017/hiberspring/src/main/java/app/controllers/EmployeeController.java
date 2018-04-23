package app.controllers;

import app.domain.dtos.xml.importDtos.EmployeeImportXMLDto;
import app.domain.dtos.xml.importDtos.EmployeeWrapperImportXMLDto;
import app.parsers.JsonParser;
import app.parsers.XmlParser;
import app.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;
    private final XmlParser xmlParser;
    private final JsonParser jsonParser;

    @Autowired
    public EmployeeController(EmployeeService employeeService, XmlParser xmlParser, JsonParser jsonParser) {
        this.employeeService = employeeService;
        this.xmlParser = xmlParser;
        this.jsonParser = jsonParser;
    }

    public String importEmployeesFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        EmployeeWrapperImportXMLDto employees = this.xmlParser.read(EmployeeWrapperImportXMLDto.class, xmlContent);

        for (EmployeeImportXMLDto employee : employees.getEmployees()) {
            try {
                this.employeeService.create(employee);
                sb.append(String.format("Successfully imported Employee %s %s.", employee.getFirstName(), employee.getLastName()));
                sb.append(System.lineSeparator());
            } catch (Exception e) {
                sb.append("Error: Invalid data.");
                sb.append(System.lineSeparator());
            }
        }

        return sb.toString();
    }

    public String getProductiveEmployeesInJson() {
        return this.jsonParser.write(this.employeeService.productiveEmployees());
    }
}
