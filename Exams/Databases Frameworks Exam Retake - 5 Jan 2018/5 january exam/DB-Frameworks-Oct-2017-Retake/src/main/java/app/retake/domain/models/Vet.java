package app.retake.domain.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vets")
public class Vet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "profession", nullable = false)
    private String profession;

    @Basic
    private Integer age;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @OneToMany(mappedBy = "vet", cascade = CascadeType.ALL)
    private Set<Procedure> procedures;

    public Vet() {
        this.procedures = new HashSet<>();
    }

    public Vet(String name, String profession, Integer age, String phoneNumber, Set<Procedure> procedures) {
        this.name = name;
        this.profession = profession;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.procedures = procedures;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }
}
