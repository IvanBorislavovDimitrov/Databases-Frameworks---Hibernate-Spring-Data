package app.entitites.dtos.queries.local_suppliers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SupplierShortInfoWrapperDto {

    @XmlElement(name = "supplier")
    private List<SupplierShortInfoDto> supplierShortInfoDtos;

    public SupplierShortInfoWrapperDto() {
        this.supplierShortInfoDtos = new ArrayList<>();
    }

    public List<SupplierShortInfoDto> getSupplierShortInfoDtos() {
        return supplierShortInfoDtos;
    }

    public void setSupplierShortInfoDtos(List<SupplierShortInfoDto> supplierShortInfoDtos) {
        this.supplierShortInfoDtos = supplierShortInfoDtos;
    }
}
