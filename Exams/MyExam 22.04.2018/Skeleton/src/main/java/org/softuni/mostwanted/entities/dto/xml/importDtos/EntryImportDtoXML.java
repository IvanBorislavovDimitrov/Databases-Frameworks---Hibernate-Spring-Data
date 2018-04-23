package org.softuni.mostwanted.entities.dto.xml.importDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EntryImportDtoXML {

    @XmlAttribute(name = "id")
    private Integer id;

    public EntryImportDtoXML() {
    }

    public Integer getId() {
        return id;
    }

    public void setEntry(Integer id) {
        this.id = id;
    }
}
