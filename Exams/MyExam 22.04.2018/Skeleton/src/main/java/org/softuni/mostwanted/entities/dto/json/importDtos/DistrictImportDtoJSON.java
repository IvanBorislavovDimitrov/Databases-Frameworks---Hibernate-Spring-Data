package org.softuni.mostwanted.entities.dto.json.importDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class DistrictImportDtoJSON {

    @Expose
    @NotNull
    private String name;

    @Expose
    @NotNull
    private String townName;

    public DistrictImportDtoJSON() {
    }

    public DistrictImportDtoJSON(String name, String townName) {
        this.name = name;
        this.townName = townName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
