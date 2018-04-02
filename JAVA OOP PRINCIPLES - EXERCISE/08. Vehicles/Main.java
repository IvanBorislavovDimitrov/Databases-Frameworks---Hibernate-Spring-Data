package java_fundamentals.vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] carInfo = input.readLine().split("\\s+");
        String[] truckInfo = input.readLine().split("\\s+");

        Vehicle car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]));
        Vehicle truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]));

        int count = Integer.parseInt(input.readLine());

        for (int i = 0; i < count; i++) {

            String[] infoAboutCommand = input.readLine().split("\\s+");
            String command = infoAboutCommand[0];
            String vehicleType = infoAboutCommand[1];



            if (command.equals("Drive")) {
                double kilometres = 0;

                if (infoAboutCommand.length == 3) {
                    kilometres = Double.parseDouble(infoAboutCommand[2]);
                }

                if (vehicleType.equals("Car")) {
                    car.drive(kilometres);
                } else if (vehicleType.equals("Truck")) {
                    truck.drive(kilometres);
                }
            } else if (command.equals("Refuel")) {
                double fuelQuantity = 0;

                if (infoAboutCommand.length == 3) {
                    fuelQuantity = Double.parseDouble(infoAboutCommand[2]);
                }

                if (vehicleType.equals("Car")) {
                    car.refuel(fuelQuantity);
                } else if (vehicleType.equals("Truck")) {
                    truck.refuel(fuelQuantity);
                }
            }
        }

        System.out.println(car);
        System.out.println(truck);
    }
}
