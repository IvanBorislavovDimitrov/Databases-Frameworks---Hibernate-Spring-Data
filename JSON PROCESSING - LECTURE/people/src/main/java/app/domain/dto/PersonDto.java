package app.domain.dto;

import app.domain.model.PhoneNumber;
import com.google.gson.annotations.Expose;

import java.util.List;

public class PersonDto {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private AddressDto addressImportDto;

    @Expose
    private List<PhoneNumberDto> phoneJsonDtos;

    public PersonDto() {
    }

    public PersonDto(String firstName, String lastName, AddressDto addressImportDto, List<PhoneNumberDto> phoneJsonDtos) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressImportDto = addressImportDto;
        this.phoneJsonDtos = phoneJsonDtos;
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

    public AddressDto getAddressImportDto() {
        return addressImportDto;
    }

    public void setAddressImportDto(AddressDto addressImportDto) {
        this.addressImportDto = addressImportDto;
    }

    public List<PhoneNumberDto> getPhoneJsonDtos() {
        return phoneJsonDtos;
    }

    public void setPhoneJsonDtos(List<PhoneNumberDto> phoneJsonDtos) {
        this.phoneJsonDtos = phoneJsonDtos;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.getFirstName(), this.getLastName(), this.getAddressImportDto().getCountry());
    }
}
