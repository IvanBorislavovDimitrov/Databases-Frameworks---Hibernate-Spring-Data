package entities;

import javax.persistence.Entity;

@Entity
public class Car extends PassengerVehicle {
    private final static String model = "CAR";
    public Car(){
        super(model);
    }
}
