package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class MostPopularItemDTO {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "total-made")
    private BigDecimal totalMade;

    @XmlElement(name = "times-sold")
    private Integer timesSold;

    public MostPopularItemDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalMade() {
        return totalMade;
    }

    public void setTotalMade(BigDecimal totalMade) {
        this.totalMade = totalMade;
    }

    public Integer getTimesSold() {
        return timesSold;
    }

    public void setTimesSold(Integer timesSold) {
        this.timesSold = timesSold;
    }
}
