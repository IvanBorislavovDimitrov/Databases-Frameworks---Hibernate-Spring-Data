package app.exam.service.imp;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.domain.dto.xml.OrderItemXMLImportDTO;
import app.exam.domain.entities.Employee;
import app.exam.domain.entities.Position;
import app.exam.parser.ModelParserImpl;
import app.exam.parser.ValidationUtil;
import app.exam.repository.EmployeeRepository;
import app.exam.repository.PositionRepository;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PositionRepository positionRepository;
    private final ModelParserImpl mapper;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository, PositionRepository positionRepository, ModelParserImpl mapper) {
        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(EmployeeJSONImportDTO importDTO) throws IllegalArgumentException {
        if (! ValidationUtil.isValid(importDTO)) {
            throw new IllegalArgumentException();
        }
        Employee employee = this.mapper.convert(importDTO, Employee.class);
        Position position = this.positionRepository.findByName(importDTO.getPosition());
        if (position == null) {
            position = new Position();
            position.setName(importDTO.getPosition());
        }

        this.positionRepository.save(position);
        employee.setPosition(position);
        if (! ValidationUtil.isValid(employee)) {
            throw new IllegalArgumentException();
        }
        this.employeeRepository.save(employee);
    }

    @Override
    public void createMany(EmployeeJSONImportDTO[] importDTO) {
    }
}
