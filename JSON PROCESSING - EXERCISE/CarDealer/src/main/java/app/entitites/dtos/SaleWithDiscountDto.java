package app.entitites.dtos;

public class SaleWithDiscountDto {

    private CarInfoDto1 car;
    private String customerName;
    private double discount;
    private double price;
    private double priceWithDiscount;

    public SaleWithDiscountDto() {
    }

    public SaleWithDiscountDto(CarInfoDto1 car, String customerName, double discount, double price, double priceWithDicount) {
        this.car = car;
        this.customerName = customerName;
        this.discount = discount;
        this.price = price;
        this.priceWithDiscount = priceWithDicount;
    }

    public CarInfoDto1 getCar() {
        return car;
    }

    public void setCar(CarInfoDto1 car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
