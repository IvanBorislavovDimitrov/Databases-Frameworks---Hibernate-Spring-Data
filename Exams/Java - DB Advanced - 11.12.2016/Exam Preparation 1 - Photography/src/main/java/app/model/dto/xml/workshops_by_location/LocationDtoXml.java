package app.model.dto.xml.workshops_by_location;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "workshops")
public class LocationDtoXml {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "workshop")
    private List<WorkshopDtoXml> workshopDtos;

    public LocationDtoXml() {
        this.workshopDtos = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<WorkshopDtoXml> getWorkshopDtos() {
        return workshopDtos;
    }

    public void setWorkshopDtos(List<WorkshopDtoXml> workshopDtos) {
        this.workshopDtos = workshopDtos;
    }
}
