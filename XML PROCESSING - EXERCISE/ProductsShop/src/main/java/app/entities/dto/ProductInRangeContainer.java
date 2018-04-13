package app.entities.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductInRangeContainer {
    @XmlElement(name = "product")
    private List<ProductsInRangeDto> productsInRangeDtos;

    public ProductInRangeContainer() {
    }
    public List<ProductsInRangeDto> getProductsInRangeDtos() {
        return productsInRangeDtos;
    }
    public void setProductsInRangeDtos(List<ProductsInRangeDto> productsInRangeDtos) {
        this.productsInRangeDtos = productsInRangeDtos;
    }


}