package app.entities.dto;

public class ProductShortInfo {

    private String name;
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
