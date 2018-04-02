package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {

    public Car() {
        super("Car");
    }
}
