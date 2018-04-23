package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PersonImportJSONDto implements Serializable {

    @Expose
    @NotNull
    private String name;

    @Expose
    @NotNull
    private String homePlanet;

    public PersonImportJSONDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomePlanet() {
        return homePlanet;
    }

    public void setHomePlanet(String homePlanet) {
        this.homePlanet = homePlanet;
    }
}
