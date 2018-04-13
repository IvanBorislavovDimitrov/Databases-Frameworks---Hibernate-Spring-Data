package app.entities.dtos.dtos_suppliers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SuppliersDto {

    @XmlElement(name = "supplier")
    private List<SupplierDto> suppliersDtos;

    public SuppliersDto() {
        this.suppliersDtos = new ArrayList<>();
    }

    public SuppliersDto(List<SupplierDto> suppliersDtos) {
        this.suppliersDtos = suppliersDtos;
    }

    public List<SupplierDto> getSuppliersDtos() {
        return suppliersDtos;
    }

    public void setSuppliersDtos(List<SupplierDto> suppliersDtos) {
        this.suppliersDtos = suppliersDtos;
    }
}