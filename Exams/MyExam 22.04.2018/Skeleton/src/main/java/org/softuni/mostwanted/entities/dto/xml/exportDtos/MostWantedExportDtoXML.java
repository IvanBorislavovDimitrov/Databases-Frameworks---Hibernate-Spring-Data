package org.softuni.mostwanted.entities.dto.xml.exportDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "most-wanted")
public class MostWantedExportDtoXML {

    @XmlElement(name = "racer")
    private RacerExportDtoXML racer;

    public MostWantedExportDtoXML() {
        this.racer = new RacerExportDtoXML();
    }

    public RacerExportDtoXML getRacer() {
        return racer;
    }

    public void setRacer(RacerExportDtoXML racer) {
        this.racer = racer;
    }
}
