package app.model.dto.xml.photographers_with_same_camera_make;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographerDtoXml {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "primary-camera")
    private String primaryCamera;

    @XmlElementWrapper(name = "lens")
    @XmlElement(name = "len")
    private List<LenDtoXml> lens;

    public PhotographerDtoXml() {
        this.lens = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryCamera() {
        return primaryCamera;
    }

    public void setPrimaryCamera(String primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public List<LenDtoXml> getLens() {
        return lens;
    }

    public void setLens(List<LenDtoXml> lens) {
        this.lens = lens;
    }
}
