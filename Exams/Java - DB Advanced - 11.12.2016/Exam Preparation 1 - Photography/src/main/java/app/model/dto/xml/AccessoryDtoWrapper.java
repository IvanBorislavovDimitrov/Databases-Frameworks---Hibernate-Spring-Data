package app.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "accessories")
public class AccessoryDtoWrapper {

    @XmlElement(name = "accessory")
    private List<AccessoryDto> accessoryDtos;

    public AccessoryDtoWrapper() {
        this.accessoryDtos = new ArrayList<>();
    }

    public List<AccessoryDto> getAccessoryDtos() {
        return accessoryDtos;
    }

    public void setAccessoryDtos(List<AccessoryDto> accessoryDtos) {
        this.accessoryDtos = accessoryDtos;
    }
}
