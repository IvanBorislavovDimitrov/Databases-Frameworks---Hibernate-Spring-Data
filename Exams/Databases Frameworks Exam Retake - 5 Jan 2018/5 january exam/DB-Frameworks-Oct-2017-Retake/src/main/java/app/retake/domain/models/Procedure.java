package app.retake.domain.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "procedures")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(mappedBy = "procedures", cascade = CascadeType.ALL)
    private Set<AnimalAid> services;

    @ManyToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "vet_id", referencedColumnName = "id")
    private Vet vet;

    @Basic
    private Date date;

    @Transient
    public BigDecimal getCost() {
        BigDecimal price = BigDecimal.ZERO;
        for (AnimalAid service : this.services) {
            price =  price.add(service.getPrice());
        }

        return price;
    }

    public Procedure() {
        this.services = new HashSet<>();
    }

    public Procedure(Set<AnimalAid> services, Animal animal, Vet vet, Date date) {
        this.services = services;
        this.animal = animal;
        this.vet = vet;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<AnimalAid> getServices() {
        return services;
    }

    public void setServices(Set<AnimalAid> services) {
        this.services = services;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Vet getVet() {
        return vet;
    }

    public void setVet(Vet vet) {
        this.vet = vet;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
