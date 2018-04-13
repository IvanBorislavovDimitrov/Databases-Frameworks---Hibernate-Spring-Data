package app.entitites.dtos.queries.ordered_customers;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlAccessorType(value = XmlAccessType.FIELD)
public class SaleDto implements Serializable {

    @XmlElement(name = "discount")
    private double discount;

    @XmlElement(name = "car-name")
    private String carName;

    public SaleDto() {
    }

    public SaleDto(double discount, String carName) {
        this.discount = discount;
        this.carName = carName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }
}
