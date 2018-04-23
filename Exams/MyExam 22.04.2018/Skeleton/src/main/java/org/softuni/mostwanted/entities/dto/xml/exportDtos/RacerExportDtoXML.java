package org.softuni.mostwanted.entities.dto.xml.exportDtos;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "entries")
public class RacerExportDtoXML {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "entry")
    private List<EntryExportDtoXML> entries;

    public RacerExportDtoXML() {
        this.entries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntryExportDtoXML> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryExportDtoXML> entries) {
        this.entries = entries;
    }
}
