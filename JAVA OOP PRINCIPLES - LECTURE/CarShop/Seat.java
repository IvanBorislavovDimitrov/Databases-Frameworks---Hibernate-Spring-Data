package car_shop;

import java.io.Serializable;

public class Seat implements Serializable, Car {

    private String countryProduced;
    private String model;
    private String color;
    private int horsePower;

    public Seat(String model,  String color, int horsePower, String countryProduced) {
        this.countryProduced = countryProduced;
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires", this.getModel(), this.countryProduced, Car.tires);
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
}
