package org.softuni.mostwanted.entities.dto.xml.exportDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class EntryExportDtoXML {

    @XmlElement(name = "finish-time")
    private Double finishTime;

    @XmlElement(name = "car")
    private String car;

    public EntryExportDtoXML() {
    }

    public Double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Double finishTime) {
        this.finishTime = finishTime;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
