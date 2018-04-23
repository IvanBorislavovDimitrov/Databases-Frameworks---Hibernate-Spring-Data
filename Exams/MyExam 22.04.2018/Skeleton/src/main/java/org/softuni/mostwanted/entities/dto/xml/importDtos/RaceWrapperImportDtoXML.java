package org.softuni.mostwanted.entities.dto.xml.importDtos;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "races")
public class RaceWrapperImportDtoXML {

    @XmlElement(name = "race")
    @Valid
    private List<RaceImportDtoXML> races;

    public RaceWrapperImportDtoXML() {
        this.races = new ArrayList<>();
    }

    public List<RaceImportDtoXML> getRaces() {
        return races;
    }

    public void setRaces(List<RaceImportDtoXML> races) {
        this.races = races;
    }
}
