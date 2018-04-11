package app.entitites.dtos;

public class SaleDto {

    private double discount;
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
