package app.domain.dtos.xml.importDtos;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "products")
public class ProductWrapperImportXMLDto {

    @XmlElement(name = "product")
    private List<ProductImportXMLDto> products;

    public ProductWrapperImportXMLDto() {
        this.products = new ArrayList<>();
    }

    public List<ProductImportXMLDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductImportXMLDto> products) {
        this.products = products;
    }
}
