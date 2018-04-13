package app.entities.dto.successfully_sold_products_dtos;

import javax.xml.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sold-products")
public class SoldProductsDto {

    @XmlElement(name = "product")
    private Set<SoldProduct> soldProducts;

    public SoldProductsDto() {
        this.soldProducts = new HashSet<>();
    }

    public Set<SoldProduct> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<SoldProduct> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
