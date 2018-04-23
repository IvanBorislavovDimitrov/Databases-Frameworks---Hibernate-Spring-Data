package app.retake.domain.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "procedures")
public class ProcedureWrapperXMLExportDTO {

    @XmlElement(name = "procedure")
    private List<ProcedureXMLExportDTO> procedures;

    public ProcedureWrapperXMLExportDTO() {
        this.procedures = new ArrayList<>();
    }

    public List<ProcedureXMLExportDTO> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProcedureXMLExportDTO> procedures) {
        this.procedures = procedures;
    }
}
