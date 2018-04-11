package app.domain.dto;

import com.google.gson.annotations.Expose;

public class PhoneNumberDto {

    @Expose
    private String number;

    public PhoneNumberDto() {
    }

    public PhoneNumberDto(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("%s", this.getNumber());
    }
}

