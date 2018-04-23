package com.masdefect.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stars")
public class Star implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "solar_system_id", referencedColumnName = "id")
    private SolarSystem solarSystem;

    @OneToMany(mappedBy = "sun")
    private Set<Planet> planets;

    public Star() {
        this.planets = new HashSet<>();
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

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public Set<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(Set<Planet> planets) {
        this.planets = planets;
    }
}
