package app.domain.dtos.xml.exportDtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "branches")
public class BranchExportWrapperXmlDto {

    @XmlElement(name = "branch")
    private List<BranchExportXmlDto> branches;

    public BranchExportWrapperXmlDto() {
        this.branches = new ArrayList<>();
    }

    public List<BranchExportXmlDto> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchExportXmlDto> branches) {
        this.branches = branches;
    }
}
