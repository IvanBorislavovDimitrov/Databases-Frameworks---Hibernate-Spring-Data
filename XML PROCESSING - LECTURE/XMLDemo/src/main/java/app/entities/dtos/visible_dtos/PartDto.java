package app.entities.dtos.visible_dtos;

import javax.xml.bind.annotation.*;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class PartDto {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "price")
    private double price;

    @XmlElement(name = "quantity")
    private int quantity;

    public PartDto() {
    }

    public PartDto(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
