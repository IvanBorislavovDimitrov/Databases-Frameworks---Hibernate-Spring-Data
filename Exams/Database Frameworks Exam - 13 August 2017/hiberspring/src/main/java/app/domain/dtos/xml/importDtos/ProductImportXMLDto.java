package app.domain.dtos.xml.importDtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportXMLDto {

    @XmlAttribute(name = "name")
    @NotNull
    private String name;

    @XmlAttribute(name = "clients")
    @NotNull
    @Min(0)
    private Integer clients;

    @XmlElement(name = "branch")
    @NotNull
    private String branchName;

    public ProductImportXMLDto() {
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClients() {
        return clients;
    }

    public void setClients(Integer clients) {
        this.clients = clients;
    }
}
