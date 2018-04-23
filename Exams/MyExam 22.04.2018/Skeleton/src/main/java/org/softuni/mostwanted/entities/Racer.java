package org.softuni.mostwanted.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "racers")
public class Racer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Basic
    private Integer age;

    @Basic
    private BigDecimal bounty;

    @ManyToOne
    @JoinColumn(name = "home_town_id", referencedColumnName = "id")
    private Town homeTown;

    @OneToMany(mappedBy = "racer", cascade = CascadeType.ALL)
    private List<Car> cars;

    @OneToMany(mappedBy = "racer", cascade = CascadeType.ALL)
    private List<RaceEntry> raceEntries;

    public Racer() {
        this.cars = new ArrayList<>();
        this.raceEntries = new ArrayList<>();
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public Town getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public List<RaceEntry> getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(List<RaceEntry> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
