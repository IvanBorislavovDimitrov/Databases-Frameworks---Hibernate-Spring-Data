package beer_counter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String line;
        try {
            while (!(line = input.readLine()).equals("End")) {
                int boughtBeers = Integer.parseInt(line.split("\\s+")[0]);
                int drankBeers = Integer.parseInt(line.split("\\s+")[1]);

                BeerCounter.buyBeer(boughtBeers);
                BeerCounter.drinkBeer(drankBeers);
            }
        } catch (Exception ignored) { }
        System.out.println(String.format("%d %d", BeerCounter.beerInStock, BeerCounter.beersDrankCount));
    }
}
