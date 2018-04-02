import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.OptionalDouble;

public class MinEvenNumber {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String str = input.readLine().trim();

        if (str.equals("")) {
            System.out.println("No match");
            return;
        }
        String[] numbs = str.split("\\s+");
        double[] numbers = Arrays.stream(numbs).mapToDouble(Double::parseDouble).toArray();
        OptionalDouble optionalDouble = Arrays.stream(numbers).filter(x -> x % 2 == 0)
                .min();

        if (optionalDouble.isPresent()) {
            System.out.println(String.format("%.2f", optionalDouble.getAsDouble()));
        } else {
            System.out.println("No match");
        }
    }
}
