package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class SolarSystemImportJSONDto implements Serializable {

    @NotNull
    @Expose
    private String name;

    public SolarSystemImportJSONDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
