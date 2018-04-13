package app.entitites.dtos.queries.ordered_customers;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlRootElement(name = "sales")
public class SetOfSalesWrapperDto implements Serializable {

    @XmlElement(name = "sale")
    private List<SaleDto> saleDtos;

    public SetOfSalesWrapperDto() {
        this.saleDtos = new ArrayList<>();
    }

    public List<SaleDto> getSaleDtos() {
        return saleDtos;
    }

    public void setSaleDtos(List<SaleDto> saleDtos) {
        this.saleDtos = saleDtos;
    }
}
