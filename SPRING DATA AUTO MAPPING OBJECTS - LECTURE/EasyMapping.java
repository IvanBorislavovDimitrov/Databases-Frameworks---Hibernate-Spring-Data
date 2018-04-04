package app;

import app.dtos.EmployeeDto;
import app.entities.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ConsoleRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        ModelMapper modelMapper = new ModelMapper();

        Employee employee = new Employee();
        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
        employee.setFirstName("Ivan");
        employee.setLastName("Dimitrov");
        employee.setSalary(new BigDecimal(520.3));
        employee.setAddress("Ulica Ivan Stranski - fanah dedoviq ");
        employee.setBirthDate(new SimpleDateFormat("dd-MMM-yyyy").parse("17-Oct-1997"));

        PropertyMap<Employee, EmployeeDto> employeeMap = new PropertyMap<Employee, EmployeeDto>() {
            @Override
            protected void configure() {
                map().setFirstName(source.getFirstName());
                map().setLastName(source.getLastName());
                map().setSalary(source.getSalary());
            }
        };

        modelMapper.addMappings(employeeMap).map(employee, employeeDto);

        System.out.println(employeeDto);
    }
}
