package java_fundamentals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StudentsByAge {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<String> people = new ArrayList<>();
        while (! (line = input.readLine()).equals("END")) {
            people.add(line);
        }

        String[] arr = people.stream().filter(x -> {
            int age = Integer.parseInt(x.split("\\s+")[2]);
            return age >= 18 && age <= 24;
        }).toArray(String[]::new);

        for (String str : arr) {
            System.out.println(str);
        }
    }
}
