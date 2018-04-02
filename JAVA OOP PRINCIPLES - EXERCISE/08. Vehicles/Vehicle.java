package java_fundamentals.vehicles;

public abstract class Vehicle {

    private double fuelQuantity;
    private double fuelConsumptionInLitresPerKm;
    private double distanceTravelled;

    public Vehicle(double fuelQuantity, double fuelConsumptionInLitresPerKm) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumptionInLitresPerKm = fuelConsumptionInLitresPerKm;
        this.distanceTravelled = 0;
    }

    public abstract void drive(double distance);

    public abstract void refuel(double quantity);

    public double getFuelQuantity() {
        return fuelQuantity;
    }

    public void setFuelQuantity(double fuelQuantity) {
        this.fuelQuantity = fuelQuantity;
    }

    public double getFuelConsumptionInLitresPerKm() {
        return fuelConsumptionInLitresPerKm;
    }

    public void setFuelConsumptionInLitresPerKm(double fuelConsumptionInLitresPerKm) {
        this.fuelConsumptionInLitresPerKm = fuelConsumptionInLitresPerKm;
    }

    public double getDistanceTravelled() {
        return distanceTravelled;
    }

    public void setDistanceTravelled(double distanceTravelled) {
        this.distanceTravelled = distanceTravelled;
    }
}
