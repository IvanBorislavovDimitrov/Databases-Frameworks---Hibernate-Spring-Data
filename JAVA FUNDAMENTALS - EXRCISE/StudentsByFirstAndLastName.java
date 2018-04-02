import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentsByGroups {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        List<String> names = new ArrayList<>();
        String line;
        while (! (line = input.readLine()).equals("END")) {
            names.add(line);
        }

        for (String name : names.stream()
                .filter(x -> {
                    String firstName = x.split("\\s+")[0];
                    String lastName = x.split("\\s+")[1];

                    return firstName.compareTo(lastName) < 0;
                })
                .collect(Collectors.toList())) {
            System.out.println(name.trim());
        }
    }
}
