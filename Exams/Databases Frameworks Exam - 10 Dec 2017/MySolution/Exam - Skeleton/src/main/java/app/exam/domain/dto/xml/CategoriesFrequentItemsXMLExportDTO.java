package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "categories")
public class CategoriesFrequentItemsXMLExportDTO {

    @XmlElement(name = "category")
    private List<CategoryExportDTO> categories;

    public CategoriesFrequentItemsXMLExportDTO() {
        this.categories = new ArrayList<>();
    }

    public List<CategoryExportDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryExportDTO> categories) {
        this.categories = categories;
    }
}
