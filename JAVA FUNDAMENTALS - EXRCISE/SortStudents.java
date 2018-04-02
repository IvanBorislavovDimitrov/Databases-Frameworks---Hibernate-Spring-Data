package java_fundamentals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SortStudents {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        List<String> names = new ArrayList<>();
        String line;
        while (! (line = input.readLine()).equals("END")) {
            names.add(line);
        }

        names.sort((x1, x2) -> {
            String x1LastName = x1.split("\\s+")[1];
            String x2LastName = x2.split("\\s+")[1];
            if (x1LastName.compareTo(x2LastName) == 0) {
                String x1FirstName = x1.split("\\s+")[0];
                String x2FirstName = x2.split("\\s+")[0];
                return x2FirstName.compareTo(x1FirstName);
            }

            return x1LastName.compareTo(x2LastName);
        });

        for (String str : names) {
            System.out.println(str);
        }
    }
}
