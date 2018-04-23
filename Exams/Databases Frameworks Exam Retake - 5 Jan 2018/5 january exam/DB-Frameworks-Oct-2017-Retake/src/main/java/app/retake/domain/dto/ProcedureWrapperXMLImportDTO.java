package app.retake.domain.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "procedures")
public class ProcedureWrapperXMLImportDTO {

    @XmlElement(name = "procedure")
    private List<ProcedureXMLImportDTO> procedures;

    public ProcedureWrapperXMLImportDTO() {
        this.procedures = new ArrayList<>();
    }

    public List<ProcedureXMLImportDTO> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProcedureXMLImportDTO> procedures) {
        this.procedures = procedures;
    }
}
