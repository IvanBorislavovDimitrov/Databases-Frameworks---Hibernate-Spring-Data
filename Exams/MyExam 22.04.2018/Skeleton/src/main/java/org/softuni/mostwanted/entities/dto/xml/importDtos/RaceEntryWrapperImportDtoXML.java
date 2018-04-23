package org.softuni.mostwanted.entities.dto.xml.importDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "race-entries")
public class RaceEntryWrapperImportDtoXML {

    @XmlElement(name = "race-entry")
    private List<RaceEntryImportDtoXML> raceEntries;

    public RaceEntryWrapperImportDtoXML() {
        this.raceEntries = new ArrayList<>();
    }

    public List<RaceEntryImportDtoXML> getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(List<RaceEntryImportDtoXML> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
