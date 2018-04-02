package ferrari;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.instrument.IllegalClassFormatException;

public class Main {

    public static void main(String[] args) throws IllegalClassFormatException, IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Ferrari ferrari = new Ferrari(input.readLine());
        System.out.println(String.format("%s/%s/%s/%s", Ferrari.name, ferrari.useBreaks(), ferrari.pushGasPedal(),
                ferrari.getDriverName()));

        String ferrariName = Ferrari.class.getSimpleName();
        String carInterface = Car.class.getSimpleName();
        boolean isCreated = Car.class.isInterface();
        if (!isCreated) {
            throw new IllegalClassFormatException("No interface created!");
        }

    }
}
