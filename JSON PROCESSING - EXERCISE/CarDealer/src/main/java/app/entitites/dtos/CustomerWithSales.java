package app.entitites.dtos;

import java.util.HashSet;
import java.util.Set;

public class CustomerWithSales {

    private Long id;
    private String name;
    private String birthDate;
    private boolean isYoungDriver;
    private Set<SaleDto> saleDtos;

    public CustomerWithSales() {
        this.saleDtos = new HashSet<>();
    }

    public CustomerWithSales(Long id, String name, String birthDate, boolean isYoungDriver, Set<SaleDto> saleDtos) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
        this.saleDtos = saleDtos;
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

    public Set<SaleDto> getSaleDtos() {
        return saleDtos;
    }

    public void setSaleDtos(Set<SaleDto> saleDtos) {
        this.saleDtos = saleDtos;
    }
}
