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
                .filter(x -> Integer.parseInt(x.split("\\s+")[2]) == 2)
                .sorted((x1, x2) -> {
                    String firstName = x1.split("\\s+")[0];
                    String secondName = x2.split("\\s+")[0];

                    return firstName.compareTo(secondName);
                })
                .collect(Collectors.toList())) {
            System.out.println(name.trim().substring(0, name.length() - 2));
        }
    }
}
