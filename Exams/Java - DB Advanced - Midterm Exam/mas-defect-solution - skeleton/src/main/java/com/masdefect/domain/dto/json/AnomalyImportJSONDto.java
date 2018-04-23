package com.masdefect.domain.dto.json;


import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AnomalyImportJSONDto implements Serializable {

    @Expose
    @NotNull
    private String originPlanet;

    @NotNull
    @Expose
    private String teleportPlanet;

    public AnomalyImportJSONDto() {
    }

    public String getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(String originPlanet) {
        this.originPlanet = originPlanet;
    }

    public String getTeleportPlanet() {
        return teleportPlanet;
    }

    public void setTeleportPlanet(String teleportPlanet) {
        this.teleportPlanet = teleportPlanet;
    }
}
