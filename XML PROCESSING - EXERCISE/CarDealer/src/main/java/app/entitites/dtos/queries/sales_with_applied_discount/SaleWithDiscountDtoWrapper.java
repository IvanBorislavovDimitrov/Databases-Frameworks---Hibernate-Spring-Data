package app.entitites.dtos.queries.sales_with_applied_discount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sales")
public class SaleWithDiscountDtoWrapper {

    @XmlElement(name = "sale")
    private List<SaleWithDiscountDto> saleWithDiscountDtos;

    public SaleWithDiscountDtoWrapper() {
    }

    public List<SaleWithDiscountDto> getSaleWithDiscountDtos() {
        return saleWithDiscountDtos;
    }

    public void setSaleWithDiscountDtos(List<SaleWithDiscountDto> saleWithDiscountDtos) {
        this.saleWithDiscountDtos = saleWithDiscountDtos;
    }
}
