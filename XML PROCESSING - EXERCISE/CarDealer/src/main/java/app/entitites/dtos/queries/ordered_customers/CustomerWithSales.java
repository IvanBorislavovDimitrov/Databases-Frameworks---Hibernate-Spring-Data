package app.entitites.dtos.queries.ordered_customers;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class CustomerWithSales implements Serializable {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    @XmlElement(name = "sales")
    private SetOfSalesWrapperDto setOfSalesWrapperDto;

    public CustomerWithSales() {
        this.setOfSalesWrapperDto = new SetOfSalesWrapperDto();
    }

    public CustomerWithSales(Long id, String name, String birthDate, boolean isYoungDriver) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public SetOfSalesWrapperDto getSetOfSalesWrapperDto() {
        return setOfSalesWrapperDto;
    }

    public void setSetOfSalesWrapperDto(SetOfSalesWrapperDto setOfSalesWrapperDto) {
        this.setOfSalesWrapperDto = setOfSalesWrapperDto;
    }
}
