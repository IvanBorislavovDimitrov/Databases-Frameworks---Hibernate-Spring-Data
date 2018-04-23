package org.softuni.mostwanted.entities.dto.xml.importDtos;

import javax.print.attribute.standard.MediaSize;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportDtoXML {

    @XmlElement(name = "laps")
    private Integer laps;

    @XmlElement(name = "district-name")
    @NotNull
    private String districtName;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<EntryImportDtoXML> entries;

    public RaceImportDtoXML() {
        this.laps = 0;
        this.entries = new ArrayList<>();
    }

    public Integer getLaps() {
        return laps;
    }

    public void setLaps(Integer laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<EntryImportDtoXML> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryImportDtoXML> entries) {
        this.entries = entries;
    }
}
