package app.model.dto.xml.workshops_by_location;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "locations")
public class LocationDtoWrapperXml {

    @XmlElement(name = "location")
    private List<LocationDtoXml> locationDtos;

    public LocationDtoWrapperXml() {
        this.locationDtos = new ArrayList<>();
    }

    public List<LocationDtoXml> getLocationDtos() {
        return locationDtos;
    }

    public void setLocationDtos(List<LocationDtoXml> locationDtos) {
        this.locationDtos = locationDtos;
    }
}
