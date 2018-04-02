package java_fundamentals.vehicles;

import java.text.DecimalFormat;

public class Car extends Vehicle {

    DecimalFormat format;

    public Car(double fuelQuantity, double fuelConsumptionInLitresPerKm) {
        super(fuelQuantity, fuelConsumptionInLitresPerKm);
        this.format = new DecimalFormat("#.################");
    }

    @Override
    public void drive(double distance) {
        try {

            double litresNeeded = (distance * (this.getFuelConsumptionInLitresPerKm() + 0.9));

            if (this.getFuelQuantity() >= litresNeeded) {
                this.setFuelQuantity(this.getFuelQuantity() - litresNeeded);
                this.setDistanceTravelled(this.getDistanceTravelled() + distance);
                System.out.println(String.format("Car travelled %s km", format.format(distance)));

            } else {
                System.out.println("Car needs refueling");
            }
        } catch (Exception e) {}
    }

    @Override
    public void refuel(double quantity) {
        this.setFuelQuantity(this.getFuelQuantity() + quantity);
    }

    @Override
    public String toString() {
        return String.format("Car: %.2f", this.getFuelQuantity());
    }
}
