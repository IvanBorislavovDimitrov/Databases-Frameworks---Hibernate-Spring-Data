import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AverageOfDoubles {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        
        
        
        String line = input.readLine();

        if (line.equals("")) {
            System.out.println("No match");
            return;
        }

        List<String> elements = Arrays.asList(line.split("\\s+"));


        OptionalDouble grade = elements.stream()
                .mapToDouble(Double::parseDouble)
                .average();

            System.out.println(String.format("%.2f", grade.getAsDouble()));
    }
}
