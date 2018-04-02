package java_fundamentals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FilterStudentsByEmailDomain {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        List<String> names = new ArrayList<>();
        String line;
        while (! (line = input.readLine()).equals("END")) {
            names.add(line);
        }

        for (String str : names
                .stream()
                .filter(x -> x.endsWith("@gmail.com"))
                .map(x -> x.substring(0, x.lastIndexOf(" ")))
                .toArray(String[]::new)) {
            System.out.println(str);
        }
    }
}
