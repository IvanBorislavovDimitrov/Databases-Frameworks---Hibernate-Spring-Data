package app.entities.dto.for_xml.categories_by_products_count;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "categories")
public class CategoriesByProductsDto {

    @XmlElement(name = "category")
    private List<CategoryByProduct> categoriesByProduct;

    public CategoriesByProductsDto() {
        this.categoriesByProduct = new ArrayList<>();
    }

    public List<CategoryByProduct> getCategoriesByProduct() {
        return categoriesByProduct;
    }

    public void setCategoriesByProduct(List<CategoryByProduct> categoriesByProduct) {
        this.categoriesByProduct = categoriesByProduct;
    }
}
