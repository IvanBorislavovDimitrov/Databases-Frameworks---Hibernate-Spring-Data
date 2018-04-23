package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class PassportJSONImportDTO implements Serializable {

    @Expose
    @Pattern(regexp = "^.{7}[0-9]{3}$")
    private String serialNumber;

    @Expose
    @NotNull
    @Size(min = 3, max = 30)
    private String ownerName;

    @Expose
    @NotNull
    @Pattern(regexp = "^(0|\\+359)[0-9]{9}$")
    private String ownerPhoneNumber;

    @Expose
    private String registrationDate;

    public PassportJSONImportDTO() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getDate() {
        return registrationDate;
    }

    public void setDate(String date) {
        this.registrationDate = date;
    }
}
