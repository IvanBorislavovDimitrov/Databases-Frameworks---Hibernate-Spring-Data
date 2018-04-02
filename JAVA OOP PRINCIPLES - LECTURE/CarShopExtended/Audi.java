package CarShop;

public class Audi implements Rentable {

    private Integer midRendDay;
    private Double pricePerDay;
    private String countryProduced;
    private String model;
    private String color;
    private int horsePower;

    public Audi(String model, String color, int horsePower, String countryProduced, int midRendDay, double pricePerDay) {
        this.model = model;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
        this.midRendDay = midRendDay;
        this.pricePerDay = pricePerDay;
        this.color = color;
    }

    @Override
    public Integer getMinRendDay() {
        return this.midRendDay;
    }

    @Override
    public Double getPricePerDay() {
        return this.pricePerDay;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public int getHorsePower() {
        return this.horsePower;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires", this.getModel(), this.countryProduced, Car.TIRES);
    }
}
