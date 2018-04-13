package app.entities.dtos.visible_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class Parts {

    @XmlElement(name = "part")
    private List<PartDto> partDtos;

    public Parts() {
        this.partDtos = new ArrayList<>();
    }

    public Parts(List<PartDto> partDtos) {
        this.partDtos = partDtos;
    }

    public List<PartDto> getPartDtos() {
        return partDtos;
    }

    public void setPartDtos(List<PartDto> partDtos) {
        this.partDtos = partDtos;
    }
}
