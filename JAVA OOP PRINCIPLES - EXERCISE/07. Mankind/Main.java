package mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] line = input.readLine().split("\\s+");
            System.out.println(new Student(line[0], line[1], line[2]));

            line = input.readLine().split("\\s+");
            System.out.println(new Worker(line[0], line[1], Double.parseDouble(line[2]), Double.parseDouble(line[3])));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
