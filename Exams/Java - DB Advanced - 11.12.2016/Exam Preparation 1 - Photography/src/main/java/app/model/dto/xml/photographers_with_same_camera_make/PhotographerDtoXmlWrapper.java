package app.model.dto.xml.photographers_with_same_camera_make;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "photographers")
public class PhotographerDtoXmlWrapper {

    @XmlElement(name = "photographer")
    private List<PhotographerDtoXml> photographerDtoXmls;

    public PhotographerDtoXmlWrapper() {
        this.photographerDtoXmls = new ArrayList<>();
    }

    public List<PhotographerDtoXml> getPhotographerDtoXmls() {
        return photographerDtoXmls;
    }

    public void setPhotographerDtoXmls(List<PhotographerDtoXml> photographerDtoXmls) {
        this.photographerDtoXmls = photographerDtoXmls;
    }
}
