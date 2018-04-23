package app.domain.dtos.xml.exportDtos;

import com.sun.xml.internal.org.jvnet.fastinfoset.FastInfosetException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TownExportXml {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "population")
    private Integer population;

    @XmlAttribute(name = "town_clinets")
    private Integer townClients;

    public TownExportXml() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getTownClients() {
        return townClients;
    }

    public void setTownClients(Integer townClients) {
        this.townClients = townClients;
    }
}
