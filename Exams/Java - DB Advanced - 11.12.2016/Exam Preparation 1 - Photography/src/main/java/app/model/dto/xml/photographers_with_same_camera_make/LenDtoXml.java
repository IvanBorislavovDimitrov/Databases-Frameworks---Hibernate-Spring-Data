package app.model.dto.xml.photographers_with_same_camera_make;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class LenDtoXml {

    @XmlAttribute(name = "make")
    private String make;

    @XmlAttribute(name = "focal-length")
    private Integer focalLength;

    @XmlAttribute(name = "max-aperture")
    private BigDecimal maxAperture;

    public LenDtoXml() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(Integer focalLength) {
        this.focalLength = focalLength;
    }

    public BigDecimal getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(BigDecimal maxAperture) {
        this.maxAperture = maxAperture;
    }
}
