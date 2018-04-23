package com.masdefect.domain.dto.xml;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class VictimXMLDto {

    @XmlAttribute(name = "name")
    private String name;

    public VictimXMLDto() {
    }

    public String getVictim() {
        return name;
    }

    public void setVictim(String victim) {
        this.name = victim;
    }
}
