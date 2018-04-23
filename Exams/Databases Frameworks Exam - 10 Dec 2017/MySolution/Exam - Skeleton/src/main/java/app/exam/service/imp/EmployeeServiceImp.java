package app.exam.service.imp;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.domain.entities.Employee;
import app.exam.domain.entities.Position;
import app.exam.parser.ValidationUtil;
import app.exam.parser.interfaces.ModelParser;
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
    private final ModelParser modelParser;
    private final PositionRepository positionRepository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository, ModelParser modelParser, PositionRepository positionRepository, PositionRepository positionRepository1) {
        this.employeeRepository = employeeRepository;
        this.modelParser = modelParser;
        this.positionRepository = positionRepository1;
    }

    @Override
    public void create(EmployeeJSONImportDTO importDTO) {
        if (! ValidationUtil.validate(importDTO)) {
            throw new IllegalArgumentException();
        }

        Position position = this.positionRepository.findByName(importDTO.getPosition());
        if (position == null) {
            position = new Position();
            position.setName(importDTO.getName());
            this.positionRepository.saveAndFlush(position);
        }

        Employee employee = this.modelParser.convert(importDTO, Employee.class);
        employee.setPosition(position);
        this.employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void createMany(EmployeeJSONImportDTO[] importDTO) {

    }
}
