package app.service.api;

import app.domain.dtos.json.exportDtos.ProductiveEmployeeDto;
import app.domain.dtos.xml.importDtos.EmployeeImportXMLDto;

import java.util.List;

public interface EmployeeService {

    void create(EmployeeImportXMLDto employeeDto);

    List<ProductiveEmployeeDto> productiveEmployees();
}
