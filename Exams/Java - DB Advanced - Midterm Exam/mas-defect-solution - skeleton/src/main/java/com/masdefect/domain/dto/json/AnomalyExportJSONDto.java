package com.masdefect.domain.dto.json;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AnomalyExportJSONDto implements Serializable {

    @Expose
    private Integer id;

    @Expose
    private PlanetExportJSONDto originPlanet;

    @Expose
    private PlanetExportJSONDto teleoprtPlanet;

    public AnomalyExportJSONDto() {
        this.originPlanet = new PlanetExportJSONDto();
        this.teleoprtPlanet = new PlanetExportJSONDto();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PlanetExportJSONDto getOriginPlanet() {
        return originPlanet;
    }

    public void setOriginPlanet(PlanetExportJSONDto originPlanet) {
        this.originPlanet = originPlanet;
    }

    public PlanetExportJSONDto getTeleoprtPlanet() {
        return teleoprtPlanet;
    }

    public void setTeleoprtPlanet(PlanetExportJSONDto teleoprtPlanet) {
        this.teleoprtPlanet = teleoprtPlanet;
    }
}
