package app.retake.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "animal_aids")
public class AnimalAid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "animal_aid_procedures",
            joinColumns = @JoinColumn(name = "procedure_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "animal_aid_id", referencedColumnName = "id"))
    private Set<Procedure> procedures;

    public AnimalAid() {
        this.procedures = new HashSet<>();
    }

    public AnimalAid(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(Set<Procedure> procedures) {
        this.procedures = procedures;
    }
}
