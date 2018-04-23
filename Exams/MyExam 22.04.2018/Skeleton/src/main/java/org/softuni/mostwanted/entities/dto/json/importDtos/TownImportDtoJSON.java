package org.softuni.mostwanted.entities.dto.json.importDtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownImportDtoJSON {

    @Expose
    @NotNull
    private String name;

    public TownImportDtoJSON() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
