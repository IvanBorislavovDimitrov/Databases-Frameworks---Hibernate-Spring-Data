package app.domain.dtos.xml.exportDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownWrapperExportXml {

    @XmlElement(name = "town")
    private List<TownExportXml> towns;

    public TownWrapperExportXml() {
        this.towns = new ArrayList<>();
    }

    public List<TownExportXml> getTowns() {
        return towns;
    }

    public void setTowns(List<TownExportXml> towns) {
        this.towns = towns;
    }
}
