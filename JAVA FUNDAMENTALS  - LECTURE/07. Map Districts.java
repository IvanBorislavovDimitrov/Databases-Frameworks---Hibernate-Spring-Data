import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapDistricts {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        Map<String, List<Integer>> cities=  new HashMap<>();

        String line = input.readLine();
        String[] infoForCities = line.split("\\s+");
        for (String city : infoForCities) {
            String[] infoAboutCity = city.split(":");
            String name = infoAboutCity[0];
            int population = Integer.parseInt(infoAboutCity[1]);
            if (! cities.containsKey(name)) {
                cities.put(name, new ArrayList<>());
                cities.get(name).add(population);
            } else {
                cities.get(name).add(population);
            }
        }

        int minPopulation = Integer.parseInt(input.readLine());

        cities.entrySet()
                .stream()
                .filter(x -> sum(x.getValue()) > minPopulation)
                .sorted((x1, x2) -> Integer.compare(sum(x2.getValue()), sum(x1.getValue())))
                .forEach(x -> {
                    x.getValue().sort((x1, x2) -> Integer.compare(x2, x1));

                    System.out.print(x.getKey() + ":");
                    for (int i = 0; i < Math.min(x.getValue().size(), 5); i++) {
                        System.out.print(" " + x.getValue().get(i));
                    }
                    System.out.println();
                });
        System.out.println();
    }

    private static int sum(List<Integer> nums) {
        int sum = 0;
        for (Integer num : nums) {
            sum += num;
        }

        return sum;
    }
}
