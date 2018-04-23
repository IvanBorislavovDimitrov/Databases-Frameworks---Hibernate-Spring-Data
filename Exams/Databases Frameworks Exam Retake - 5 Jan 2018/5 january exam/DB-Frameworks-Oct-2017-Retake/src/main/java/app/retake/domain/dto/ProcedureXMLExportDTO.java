package app.retake.domain.dto;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLExportDTO {

    @XmlAttribute(name = "animal-passport")
    private String animalPassport;

    @XmlElement(name = "owner")
    private String owner;

    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    private List<ProcedureAnimalAidXMLExportDTO> animalAids;

    private String date;

    public ProcedureXMLExportDTO() {
        this.animalAids = new ArrayList<>();
    }

    public ProcedureXMLExportDTO(String animalPassport, String owner, List<ProcedureAnimalAidXMLExportDTO> animalAids) {
        this.animalPassport = animalPassport;
        this.owner = owner;
        this.animalAids = animalAids;
    }

    public String getAnimalPassport() {
        return animalPassport;
    }

    public void setAnimalPassport(String animalPassport) {
        this.animalPassport = animalPassport;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<ProcedureAnimalAidXMLExportDTO> getAnimalAids() {
        return animalAids;
    }

    public void setAnimalAids(List<ProcedureAnimalAidXMLExportDTO> animalAids) {
        this.animalAids = animalAids;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
