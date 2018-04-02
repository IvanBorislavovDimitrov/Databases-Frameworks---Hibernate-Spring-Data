package intersection_of_circles;

public class Circle {

    private double radius;
    private Center center;

    public Circle(double radius, Center center) {
        this.radius = radius;
        this.center = center;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
