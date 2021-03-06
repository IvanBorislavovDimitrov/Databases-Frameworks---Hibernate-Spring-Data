package app.entities.dto.for_xml.users_and_products;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "sold-products")
public class SoldProducts {

    @XmlAttribute(name = "count")
    private int productsCount;

    @XmlElement(name = "product")
    private List<ProductShortInfo> products;

    public SoldProducts() {
        this.products = new ArrayList<>();
    }

    public SoldProducts(int productsCount, List<ProductShortInfo> products) {
        this.productsCount = productsCount;
        this.products = products;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public List<ProductShortInfo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductShortInfo> products) {
        this.products = products;
    }
}
