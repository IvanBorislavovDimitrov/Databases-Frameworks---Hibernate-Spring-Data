package app.entities.dto;

import app.entities.User;

import java.io.Serializable;

public class ProductsInRangeDto implements Serializable {

    private String name;

    private double price;

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
