package app.exam.domain.dto.xml;

import javax.validation.Valid;
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
    @Valid
    private List<OrderItemXMLImportDTO> items;

    public OrderItemsWrapperDTO() {
        this.items = new ArrayList<>();
    }

    public List<OrderItemXMLImportDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemXMLImportDTO> items) {
        this.items = items;
    }
}
