package app.service.imp;

import app.domain.dtos.json.exportDtos.ProductiveEmployeeDto;
import app.domain.dtos.xml.importDtos.EmployeeImportXMLDto;
import app.domain.entities.Branch;
import app.domain.entities.Employee;
import app.domain.entities.EmployeeCard;
import app.repositories.EmployeeRepository;
import app.service.api.BranchService;
import app.service.api.EmployeeCardService;
import app.service.api.EmployeeService;
import app.validation.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeCardService employeeCardService;
    private final BranchService branchService;
    private final ModelMapper mapper;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository, EmployeeCardService employeeCardService, BranchService branchService) {
        this.employeeRepository = employeeRepository;
        this.employeeCardService = employeeCardService;
        this.branchService = branchService;
        this.mapper = new ModelMapper();
    }

    @Override
    public void create(EmployeeImportXMLDto employeeDto) {
        if (! ValidatorUtil.isValid(employeeDto)) {
            throw new IllegalArgumentException();
        }

        EmployeeCard employeeCard = this.employeeCardService.findByNumber(employeeDto.getCard());
        Branch branch = this.branchService.findByName(employeeDto.getBranch());
        if (employeeCard == null || branch == null) {
            throw new IllegalArgumentException();
        }

        boolean isValid = this.employeeRepository.findAll()
                .stream()
                .filter(x -> x.getCard().getNumber().equals(employeeDto.getCard()))
                .collect(Collectors.toList()).size() == 0;
        if (! isValid) {
            throw new IllegalArgumentException();
        }

        Employee employee = this.mapper.map(employeeDto, Employee.class);
        employee.setBranch(branch);
        employee.setCard(employeeCard);

        this.employeeRepository.saveAndFlush(employee);
    }

    @Override
    public List<ProductiveEmployeeDto> productiveEmployees() {
        List<Employee> employees = this.employeeRepository.findAll();
        List<ProductiveEmployeeDto> productiveEmployeesDtos = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getBranch().getProducts().size() >= 1) {
                ProductiveEmployeeDto productiveEmployeeDto = this.mapper.map(employee, ProductiveEmployeeDto.class);
                productiveEmployeeDto.setFullName(String.format("%s %s", employee.getFirstName(), employee.getLastName()));
                productiveEmployeeDto.setNumber(employee.getCard().getNumber());
                productiveEmployeesDtos.add(productiveEmployeeDto);
            }
        }

        productiveEmployeesDtos.sort((x1, x2) -> {
            if ((x1.getFullName().equals(x2.getFullName()))) {
                return Integer.compare(x1.getPosition().length(), x2.getPosition().length());
            }

            return x1.getFullName().compareTo(x2.getFullName());
        });

        return productiveEmployeesDtos;
    }
}
