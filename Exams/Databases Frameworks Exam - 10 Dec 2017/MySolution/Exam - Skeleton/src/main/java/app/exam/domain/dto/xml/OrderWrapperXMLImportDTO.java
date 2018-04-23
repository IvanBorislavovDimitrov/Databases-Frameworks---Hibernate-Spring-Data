package app.exam.domain.dto.xml;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "orders")
public class OrderWrapperXMLImportDTO {

    @XmlElement(name = "order")
    @Valid
    private List<OrderXMLImportDTO> orders;

    public OrderWrapperXMLImportDTO() {
        this.orders = new ArrayList<>();
    }

    public List<OrderXMLImportDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderXMLImportDTO> orders) {
        this.orders = orders;
    }
}
