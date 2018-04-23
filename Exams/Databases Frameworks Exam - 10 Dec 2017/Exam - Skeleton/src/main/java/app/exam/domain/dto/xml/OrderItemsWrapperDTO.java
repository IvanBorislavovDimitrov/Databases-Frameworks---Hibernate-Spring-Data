package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "items")
public class OrderItemsWrapperDTO {

    @XmlElement(name = "item")
    private List<OrderItemXMLImportDTO> orders;

    public OrderItemsWrapperDTO() {
        this.orders = new ArrayList<>();
    }

    public List<OrderItemXMLImportDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderItemXMLImportDTO> orders) {
        this.orders = orders;
    }
}
