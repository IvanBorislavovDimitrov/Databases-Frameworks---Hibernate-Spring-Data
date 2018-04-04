package app;

import app.dtos.EmployeeDto;
import app.dtos.ManagerDto;
import app.entities.Employee;
import app.services.AddressService;
import app.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private EmployeeService employeeService;
    private AddressService addressService;

    @Autowired
    public ConsoleRunner(EmployeeService employeeService, AddressService addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {

        ModelMapper modelMapper = new ModelMapper();
//        advancedMapping(modelMapper);
        projection();
    }

    private void projection() throws ParseException {
        for (Employee e : this.employeeService.findAllByBirthDateBefore
                (new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1990"))) {
            System.out.println(String.format("%s %s %.2f - Manager: [%s]",
                    e.getFirstName(), e.getLastName(), e.getSalary(), e.getManager() == null ? "no manager" : e.getManager().getLastName()));
        }
    }

    private void advancedMapping(ModelMapper modelMapper) {

        List<Employee> employees = this.employeeService.findAll();

        for (Employee employee : employees) {
            ManagerDto managerDto = modelMapper.map(employee, ManagerDto.class);
            System.out.println(managerDto.getFirstName() + " " + managerDto.getLastName());
            System.out.println("Employees: " + managerDto.getServants().size());
            for (EmployeeDto e : managerDto.getServants()) {
                System.out.println("- " + e);
            }
        }
    }
}
