package java_fundamentals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class WeakStudents {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        List<String> names = new ArrayList<>();

        String line;
        while (!(line = input.readLine()).equals("END")) {
            names.add(line);
        }

        for (String str : names
                .stream()
                .filter(x -> {
                    int count = 0;
                    for (int i = 0; i < x.length(); i++) {
                        if (Character.isDigit(x.charAt(i))) {
                            if (x.charAt(i) <= '3') {
                                count++;
                            }
                        }
                    }
                    return count >= 2;
                })
                .map(x -> {
                    for (int i = 0; i < x.length(); i++) {
                        if (Character.isDigit(x.charAt(i))) {
                            return x.substring(0, i);
                        }
                    }

                    return "";
                })
                .toArray(String[]::new)) {
            System.out.println(str);
        }
    }
}