package app.entities.dto;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsInRangeDto {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "price")
    private double price;

    @XmlAttribute(name = "seller")
    private String seller;

    public ProductsInRangeDto() {
    }

    public ProductsInRangeDto(String name, double price, String seller) {
        this.name = name;
        this.price = price;
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
