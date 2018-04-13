package app.entitites.dtos.import_dtos.suppliers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "suppliers")
public class SupplierWrapperInput {

    @XmlElement(name = "supplier")
    private List<SupplierDtoInput> supplierDtoInputs;

    public SupplierWrapperInput() {
        this.supplierDtoInputs = new ArrayList<>();
    }

    public List<SupplierDtoInput> getSupplierDtoInputs() {
        return supplierDtoInputs;
    }

    public void setSupplierDtoInputs(List<SupplierDtoInput> supplierDtoInputs) {
        this.supplierDtoInputs = supplierDtoInputs;
    }
}
