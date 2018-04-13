package app.entitites.dtos.queries.sales_with_applied_discount;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SaleWithDiscountDto {

    @XmlElement(name = "car")
    private CarInfoDto1 car;

    @XmlElement(name = "customer-name")
    private String customerName;

    @XmlElement(name = "discount")
    private double discount;

    @XmlElement(name = "price")
    private double price;

    @XmlElement(name = "price-with-discount")
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
