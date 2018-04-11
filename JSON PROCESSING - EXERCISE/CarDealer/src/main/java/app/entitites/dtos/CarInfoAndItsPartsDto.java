package app.entitites.dtos;

import java.util.List;

public class CarInfoAndItsPartsDto {

    private String make;
    private String model;
    private double travelledDistance;
    private List<PartDto> parts;

    public CarInfoAndItsPartsDto() {
    }

    public CarInfoAndItsPartsDto(String make, String model, double travelledDistance, List<PartDto> parts) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
        this.parts = parts;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public List<PartDto> getParts() {
        return parts;
    }

    public void setParts(List<PartDto> parts) {
        this.parts = parts;
    }
}
