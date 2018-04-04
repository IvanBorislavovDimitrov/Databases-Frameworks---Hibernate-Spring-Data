package app.services_imp;

import app.entities.Employee;
import app.repositories.EmployeeRepository;
import app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(Employee employee) {
        this.employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee findOne(Long id) {
        return this.employeeRepository.getOne(id);
    }

    @Override
    public List<Employee> findAll() {
        return this.employeeRepository.findAll();
    }

    @Override
    public List<Employee> findAllByBirthDateBefore(Date date) {
        return this.employeeRepository.findAllByBirthDateBefore(date);
    }
}
