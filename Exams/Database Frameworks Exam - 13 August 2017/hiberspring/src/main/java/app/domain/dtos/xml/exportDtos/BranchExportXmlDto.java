package app.domain.dtos.xml.exportDtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class BranchExportXmlDto {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "town")
    private String town;

    @XmlAttribute(name = "total_clients")
    private Integer totalClient;

    public BranchExportXmlDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Integer getTotalClient() {
        return totalClient;
    }

    public void setTotalClient(Integer totalClient) {
        this.totalClient = totalClient;
    }
}
