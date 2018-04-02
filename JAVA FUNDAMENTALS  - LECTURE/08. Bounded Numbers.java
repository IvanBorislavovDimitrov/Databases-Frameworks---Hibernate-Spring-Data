import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BoundedNumber {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] str = input.readLine().split("\\s+");

        int firstBound = Math.min(Integer.parseInt(str[0]), Integer.parseInt(str[1]));
        int secondBound = Math.max(Integer.parseInt(str[0]), Integer.parseInt(str[1]));

        int[] numbers = Arrays.stream(input.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Arrays.stream(numbers).filter(x -> x >= firstBound && x <= secondBound)
                .forEach(x -> System.out.print(x + " "));
    }
}
