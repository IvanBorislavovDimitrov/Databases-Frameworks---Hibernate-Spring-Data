package app.domain.dtos.xml.importDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employees")
public class EmployeeWrapperImportXMLDto {

    @XmlElement(name = "employee")
    private List<EmployeeImportXMLDto> employees;

    public EmployeeWrapperImportXMLDto() {
        this.employees = new ArrayList<>();
    }

    public List<EmployeeImportXMLDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeImportXMLDto> employees) {
        this.employees = employees;
    }
}
