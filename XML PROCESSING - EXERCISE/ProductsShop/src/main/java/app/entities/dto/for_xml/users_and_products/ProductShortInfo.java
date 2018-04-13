package app.entities.dto.for_xml.users_and_products;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class ProductShortInfo {

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "price")
    private double price;

    public ProductShortInfo() {
    }

    public ProductShortInfo(String name, double price) {
        this.name = name;
        this.price = price;
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
}
