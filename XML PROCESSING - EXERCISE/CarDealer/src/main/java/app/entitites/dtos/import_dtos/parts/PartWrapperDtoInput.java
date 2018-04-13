package app.entitites.dtos.import_dtos.parts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "parts")
public class PartWrapperDtoInput {

    @XmlElement(name = "part")
    private List<PartDtoImport> partDtoImports;

    public PartWrapperDtoInput() {
        this.partDtoImports = new ArrayList<>();
    }

    public List<PartDtoImport> getPartDtoImports() {
        return partDtoImports;
    }

    public void setPartDtoImports(List<PartDtoImport> partDtoImports) {
        this.partDtoImports = partDtoImports;
    }
}
