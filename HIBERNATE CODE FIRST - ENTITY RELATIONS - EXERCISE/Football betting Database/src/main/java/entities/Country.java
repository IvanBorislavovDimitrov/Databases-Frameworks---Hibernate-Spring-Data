package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table (name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToMany(mappedBy = "country", targetEntity = Town.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Town> towns;

    @ManyToOne(optional = false)
    @JoinColumn(name = "continent_id", referencedColumnName = "id")
    private Continent continent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Town> getTowns() {
        return towns;
    }

    public void setTowns(Set<Town> towns) {
        this.towns = towns;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
