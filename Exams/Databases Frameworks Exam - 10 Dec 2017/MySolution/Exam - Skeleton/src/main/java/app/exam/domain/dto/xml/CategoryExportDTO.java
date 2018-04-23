package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;

@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryExportDTO {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "most-popular-element")
    private MostPopularItemDTO mostPopularItemDTO;

    public CategoryExportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MostPopularItemDTO getMostPopularItemDTO() {
        return mostPopularItemDTO;
    }

    public void setMostPopularItemDTO(MostPopularItemDTO mostPopularItemDTO) {
        this.mostPopularItemDTO = mostPopularItemDTO;
    }
}
