package org.softuni.mostwanted.entities.dto.json.exportDtos;

import com.google.gson.annotations.Expose;

public class RacingTownExportDtoJSON {

    @Expose
    private String name;

    @Expose
    private Integer racers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRacers() {
        return racers;
    }

    public void setRacers(Integer racers) {
        this.racers = racers;
    }
}
