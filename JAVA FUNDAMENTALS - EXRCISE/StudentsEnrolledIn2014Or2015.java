package java_fundamentals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StudentsEnrolledIn2014Or2015 {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        List<String> names = new ArrayList<>();
        String line;
        while (! (line = input.readLine()).equals("END")) {
            names.add(line);
        }

        for (String str : names
                .stream()
                .filter(x -> x.split("\\s+")[0].endsWith("15") || x.split("\\s+")[0].endsWith("14"))
                .map(x -> x.substring(x.indexOf(" ") + 1))
                .toArray(String[]::new)) {
            System.out.println(str);
        }
    }
}
