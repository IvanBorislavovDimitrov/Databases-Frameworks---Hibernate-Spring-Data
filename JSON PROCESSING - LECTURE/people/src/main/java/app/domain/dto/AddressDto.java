package app.domain.dto;

import com.google.gson.annotations.Expose;

public class AddressDto {

    @Expose
    private String country;

    @Expose
    private String city;

    @Expose
    private String street;

    public AddressDto(String country, String city, String street) {
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public AddressDto() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
