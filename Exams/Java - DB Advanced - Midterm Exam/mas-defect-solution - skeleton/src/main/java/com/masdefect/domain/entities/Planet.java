package com.masdefect.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "planets")
public class Planet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String name;

    @ManyToOne
    @JoinColumn(name = "sun_id", referencedColumnName = "id")
    private Star sun;

    @ManyToOne
    @JoinColumn(name = "solar_system_id", referencedColumnName = "id")
    private SolarSystem solarSystem;

    @OneToMany(mappedBy = "originPlanet")
    private Set<Anomaly> originPlanetAnomalies;

    @OneToMany(mappedBy = "teleportPlanet")
    private Set<Anomaly> teleportPlanetAnomalies;

    @OneToMany(mappedBy = "homePlanet")
    private Set<Person> people;

    public Planet() {
        this.people = new HashSet<>();
        this.originPlanetAnomalies = new HashSet<>();
        this.teleportPlanetAnomalies = new HashSet<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Star getSun() {
        return sun;
    }

    public void setSun(Star sun) {
        this.sun = sun;
    }

    public SolarSystem getSolarSystem() {
        return solarSystem;
    }

    public void setSolarSystem(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Anomaly> getOriginPlanetAnomalies() {
        return originPlanetAnomalies;
    }

    public void setOriginPlanetAnomalies(Set<Anomaly> originPlanetAnomalies) {
        this.originPlanetAnomalies = originPlanetAnomalies;
    }

    public Set<Anomaly> getTeleportPlanetAnomalies() {
        return teleportPlanetAnomalies;
    }

    public void setTeleportPlanetAnomalies(Set<Anomaly> teleportPlanetAnomalies) {
        this.teleportPlanetAnomalies = teleportPlanetAnomalies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
