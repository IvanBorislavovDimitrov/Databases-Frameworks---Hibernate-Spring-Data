package entities;

import javax.persistence.Entity;

@Entity
public class Truck extends TransportationVehicle {
    private final static String model = "TRUCK";
    private int noOfContainers;

    public Truck() {
        super(model);
    }

    public static String getModel() {
        return model;
    }

    public int getNoOfContainers() {
        return noOfContainers;
    }

    public void setNoOfContainers(int noOfContainers) {
        this.noOfContainers = noOfContainers;
    }
}
