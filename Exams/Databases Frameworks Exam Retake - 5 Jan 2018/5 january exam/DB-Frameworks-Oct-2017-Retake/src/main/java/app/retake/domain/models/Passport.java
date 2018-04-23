package app.retake.domain.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "passports")
public class Passport implements Serializable {

    @Id
    @Column(name = "serial_number")
    private String serialNumber;

    @OneToOne(mappedBy = "passport")
    private Animal animal;

    @Column(name = "owner_phone_number", nullable = false)
    private String ownerPhoneNumber;

    private String ownerName;

    @Column(name = "register_date")
    private Date registerDate;

    public Passport() {
    }

    public Passport(String serialNumber, Animal animal, String ownerPhoneNumber, String ownerName, Date registerDate) {
        this.serialNumber = serialNumber;
        this.animal = animal;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.ownerName = ownerName;
        this.registerDate = registerDate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getOwnerPhoneNumber() {
        return ownerPhoneNumber;
    }

    public void setOwnerPhoneNumber(String ownerPhoneNumber) {
        this.ownerPhoneNumber = ownerPhoneNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}