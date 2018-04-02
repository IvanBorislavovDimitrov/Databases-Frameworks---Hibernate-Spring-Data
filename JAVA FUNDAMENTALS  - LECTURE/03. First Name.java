import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UpperStrings {

    public static void main(String[] args) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String[] elements = input.readLine().split("\\s+");

        List<String> letters = Arrays.stream(input.readLine().split("\\s+"))
                .map(String::toLowerCase).collect(Collectors.toList());

        Optional<String> names = Arrays.stream(elements).filter(x -> letters.contains(x.toLowerCase().substring(0, 1))).sorted().findFirst();

        if (names.isPresent()) {
            System.out.println(names.get());
        } else {
            System.out.println("No match");
        }
    }
}
