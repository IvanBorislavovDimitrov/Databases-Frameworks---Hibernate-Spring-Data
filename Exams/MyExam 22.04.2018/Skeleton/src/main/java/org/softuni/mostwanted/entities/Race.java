package org.softuni.mostwanted.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "races")
public class Race {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition="int default 0")
    private Integer laps;

    @ManyToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;

    @OneToMany(mappedBy = "race")
    private List<RaceEntry> entries;

    public Race() {
        this.entries = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }
}
