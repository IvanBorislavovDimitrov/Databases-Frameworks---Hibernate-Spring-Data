package app.services;

import app.entities.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeService {

    void save(Employee employee);

    Employee findOne(Long id);

    List<Employee> findAll();

    List<Employee> findAllByBirthDateBefore(Date date);

}
