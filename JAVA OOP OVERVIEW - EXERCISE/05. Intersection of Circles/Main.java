package intersection_of_circles;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] numbers = input.nextLine().split("\\s+");
        double x = Integer.parseInt(numbers[0]);
        double y = Integer.parseInt(numbers[1]);
        double radius = Integer.parseInt(numbers[2]);

        Circle c1 = new Circle(radius, new Center(x, y));
        numbers = input.nextLine().split("\\s+");
        x = Integer.parseInt(numbers[0]);
        y = Integer.parseInt(numbers[1]);
        radius = Integer.parseInt(numbers[2]);

        Circle c2 = new Circle(radius, new Center(x, y));
        String intersect = intersect(c1, c2) ? "Yes" : "No";
        System.out.println(intersect);
    }

    private static boolean intersect(Circle c1, Circle c2) {
        double distanceX = Math.abs(c1.getCenter().getX() - c2.getCenter().getX());
        double distanceY = Math.abs(c1.getCenter().getY() - c2.getCenter().getY());
        double distanceBetweenCenters = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
        if (Math.abs(c1.getRadius() + c2.getRadius()) - distanceBetweenCenters >= 0) {
            return true;
        }

        return false;
    }
}
