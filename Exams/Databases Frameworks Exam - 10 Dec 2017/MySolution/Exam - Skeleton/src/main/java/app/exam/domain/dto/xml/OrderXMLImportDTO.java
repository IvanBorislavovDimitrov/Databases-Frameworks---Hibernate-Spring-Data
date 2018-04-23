package app.exam.domain.dto.xml;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrderXMLImportDTO {

    @XmlElement(name = "customer")
    @NotNull
    private String customer;

    @XmlElement(name = "employee")
    @Size(min = 3, max = 30)
    @NotNull
    private String employee;

    @XmlElement(name = "date")
    @NotNull
    private String date;

    @XmlElement(name = "type")
    @NotNull
    private String type;

    @XmlElement(name = "items")
    private OrderItemsWrapperDTO items;

    public OrderXMLImportDTO() {
        this.items = new OrderItemsWrapperDTO();
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public OrderItemsWrapperDTO getItems() {
        return items;
    }

    public void setItems(OrderItemsWrapperDTO items) {
        this.items = items;
    }
}
