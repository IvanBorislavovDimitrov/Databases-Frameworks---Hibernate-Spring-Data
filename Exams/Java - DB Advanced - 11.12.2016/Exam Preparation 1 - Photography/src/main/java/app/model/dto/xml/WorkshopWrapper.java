package app.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "workshops")
public class WorkshopWrapper {

    @XmlElement(name = "workshop")
    private List<WorkshopDto> workshops;

    public WorkshopWrapper() {
        this.workshops = new ArrayList<>();
    }

    public List<WorkshopDto> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<WorkshopDto> workshops) {
        this.workshops = workshops;
    }
}
