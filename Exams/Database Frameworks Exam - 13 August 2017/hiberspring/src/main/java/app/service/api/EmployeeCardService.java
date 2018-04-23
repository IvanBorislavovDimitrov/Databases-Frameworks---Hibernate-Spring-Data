package app.service.api;

import app.domain.dtos.json.exportDtos.EmployeeCardExportJSONDto;
import app.domain.dtos.json.importDtos.EmployeeCardJSONDto;
import app.domain.entities.EmployeeCard;

import java.util.List;

public interface EmployeeCardService {

    void create(EmployeeCardJSONDto employeeCardDto);

    EmployeeCard findByNumber(String name);

    List<EmployeeCardExportJSONDto> freeCards();
}
