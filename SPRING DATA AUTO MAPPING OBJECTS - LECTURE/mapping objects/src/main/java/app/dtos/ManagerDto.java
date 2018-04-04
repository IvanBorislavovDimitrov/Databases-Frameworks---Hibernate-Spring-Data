package app.dtos;

import app.entities.Employee;

import java.util.List;

public class ManagerDto {

    private String firstName;

    private String lastName;

    private List<EmployeeDto> servants;

    public ManagerDto() {
    }

    public ManagerDto(String firstName, String lastName, List<EmployeeDto> servants) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.servants = servants;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<EmployeeDto> getServants() {
        return servants;
    }

    public void setServants(List<EmployeeDto> servants) {
        this.servants = servants;
    }
}
