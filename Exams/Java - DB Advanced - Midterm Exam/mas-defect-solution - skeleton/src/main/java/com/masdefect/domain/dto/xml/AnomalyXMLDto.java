package com.masdefect.domain.dto.xml;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class AnomalyXMLDto {

    @XmlAttribute(name = "origin-homePlanet")
    private String originPlanet;

    @XmlAttribute(name = "teleport-homePlanet")
    private String teleportPlanet;

    private List<VictimXMLDto> victims;

    public AnomalyXMLDto() {
        this.victims = new ArrayList<>();
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

    public List<VictimXMLDto> getVictims() {
        return victims;
    }

    public void setVictims(List<VictimXMLDto> victims) {
        this.victims = victims;
    }
}
