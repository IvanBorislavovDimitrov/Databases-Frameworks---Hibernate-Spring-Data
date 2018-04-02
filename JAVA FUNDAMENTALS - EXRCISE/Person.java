package java_fundamentals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Person {


    public static void main(String[] args) throws IOException {
        String name;
        int group;

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        List<String> people = new ArrayList<>();
        while (! (line = input.readLine()).equals("END")) {
            people.add(line);
        }

        Map<Integer, List<String>> result = people.stream()
                .collect(Collectors.groupingBy(x -> Integer.parseInt(x.substring(x.lastIndexOf(" ") + 1)),
                        Collectors.mapping(x -> x.substring(0, x.lastIndexOf(" ")), Collectors.toList())));


        for (Map.Entry<Integer, List<String>> res : result.entrySet()) {
            System.out.print(res.getKey() + " - ");
            StringBuilder sb = new StringBuilder();
            for (String str : res.getValue()) {
                sb.append(str).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            System.out.println(sb);
        }
    }
}
