package app.entitites.dtos.import_dtos.customers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customers")
public class CustomerWrapperDtoImport {

    @XmlElement(name = "customer")
    private List<CustomerDtoImport> customerDtoImports;

    public CustomerWrapperDtoImport() {
        this.customerDtoImports = new ArrayList<>();
    }

    public List<CustomerDtoImport> getCustomerDtoImports() {
        return customerDtoImports;
    }

    public void setCustomerDtoImports(List<CustomerDtoImport> customerDtoImports) {
        this.customerDtoImports = customerDtoImports;
    }
}
