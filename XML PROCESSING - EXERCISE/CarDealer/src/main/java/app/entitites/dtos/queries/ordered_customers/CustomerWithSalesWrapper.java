package app.entitites.dtos.queries.ordered_customers;


import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CustomerWithSalesWrapper implements Serializable {

    @XmlElement(name = "customer")
    private List<CustomerWithSales> customerWithSales;

    public CustomerWithSalesWrapper() {
        this.customerWithSales = new ArrayList<>();
    }

    public List<CustomerWithSales> getCustomerWithSales() {
        return customerWithSales;
    }

    public void setCustomerWithSales(List<CustomerWithSales> customerWithSales) {
        this.customerWithSales = customerWithSales;
    }
}
