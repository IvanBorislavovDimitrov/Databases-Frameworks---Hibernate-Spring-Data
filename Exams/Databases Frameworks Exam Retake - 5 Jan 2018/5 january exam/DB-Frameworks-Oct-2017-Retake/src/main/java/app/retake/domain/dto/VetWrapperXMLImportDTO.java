package app.retake.domain.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "vets")
public class VetWrapperXMLImportDTO {

    @XmlElement(name = "vet")
    private List<VetXMLImportDTO> vets;

    public VetWrapperXMLImportDTO() {
        this.vets = new ArrayList<>();
    }

    public List<VetXMLImportDTO> getVets() {
        return vets;
    }

    public void setVets(List<VetXMLImportDTO> vets) {
        this.vets = vets;
    }
}
