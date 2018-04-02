package beer_counter;

public class BeerCounter {

    public static int beerInStock = 0;
    public static int beersDrankCount = 0;

    public static void buyBeer(int beers) {
        beerInStock += beers;
    }

    public static void drinkBeer(int beers) {
        beerInStock -= beers;
        beersDrankCount += beers;
    }
}
