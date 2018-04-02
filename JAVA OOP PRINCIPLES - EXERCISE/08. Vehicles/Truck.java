package java_fundamentals.vehicles;

import java.text.DecimalFormat;

public class Truck extends Vehicle {

    DecimalFormat format;

    public Truck(double fuelQuantity, double fuelConsumptionInLitresPerKm) {
        super(fuelQuantity, fuelConsumptionInLitresPerKm);
        this.format = new DecimalFormat("#.################");
    }

    @Override
    public void drive(double distance) {
        try {
            double litresNeeded = (distance * (this.getFuelConsumptionInLitresPerKm() + 1.6));

            if (this.getFuelQuantity() >= litresNeeded) {
                this.setFuelQuantity(this.getFuelQuantity() - litresNeeded);
                this.setDistanceTravelled(this.getDistanceTravelled() + distance);
                System.out.println(String.format("Truck travelled %s km", format.format(distance)));

            } else {
                System.out.println("Truck needs refueling");
            }
        } catch (Exception e) {}
    }

    @Override
    public void refuel(double quantity) {
        this.setFuelQuantity((this.getFuelQuantity() + quantity * 0.95));
    }

    @Override
    public String toString() {
        return String.format("Truck: %.2f", this.getFuelQuantity());
    }
}
