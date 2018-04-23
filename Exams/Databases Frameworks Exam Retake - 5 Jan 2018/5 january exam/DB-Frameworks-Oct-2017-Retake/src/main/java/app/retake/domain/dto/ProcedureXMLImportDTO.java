package app.retake.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProcedureXMLImportDTO {

    @XmlElement(name = "vet")
    private String vet;

    @XmlElement(name = "animal")
    private String animal;

    @XmlElementWrapper(name = "animal-aids")
    @XmlElement(name = "animal-aid")
    private List<ProcedureAnimalAidXMLImportDTO> animalAids;

    @XmlElement(name = "date")
    private String date;

    public ProcedureXMLImportDTO() {
        this.animalAids = new ArrayList<>();
    }

    public String getVet() {
        return vet;
    }

    public void setVet(String vet) {
        this.vet = vet;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public List<ProcedureAnimalAidXMLImportDTO> getAnimalAids() {
        return animalAids;
    }

    public void setAnimalAids(List<ProcedureAnimalAidXMLImportDTO> animalAids) {
        this.animalAids = animalAids;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
