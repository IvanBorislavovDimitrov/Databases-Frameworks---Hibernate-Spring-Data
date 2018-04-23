package app.retake.domain.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Date;

public class AnimalsJSONExportDTO implements Serializable {

    @Expose
    private String ownerName;

    @Expose
    private String animalName;

    @Expose
    private String age;

    @Expose
    private String serialNumber;

    @Expose
    private Date registerOn;

    public String getOwnerName() {
        return ownerName;
    }

    public AnimalsJSONExportDTO(String ownerName, String animalName, String age, String serialNumber, Date registerOn) {
        this.ownerName = ownerName;
        this.animalName = animalName;
        this.age = age;
        this.serialNumber = serialNumber;
        this.registerOn = registerOn;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getRegisterOn() {
        return registerOn;
    }

    public void setRegisterOn(Date registerOn) {
        this.registerOn = registerOn;
    }
}
