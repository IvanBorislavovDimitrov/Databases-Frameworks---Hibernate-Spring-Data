package entities;

import javax.persistence.Entity;

@Entity
public class Truck extends Vehicle {

    public Truck() {
        super("Truck");
    }
}
