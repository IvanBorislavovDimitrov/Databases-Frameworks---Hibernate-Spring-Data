package app.entitites.dtos.queries.total_sales_by_customer;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerInfoWrapper {

    @XmlElement(name = "customer")
    private List<CustomerInfo> customerInfos;

    public CustomerInfoWrapper() {
        this.customerInfos = new ArrayList<>();
    }

    public List<CustomerInfo> getCustomerInfos() {
        return customerInfos;
    }

    public void setCustomerInfos(List<CustomerInfo> customerInfos) {
        this.customerInfos = customerInfos;
    }
}
