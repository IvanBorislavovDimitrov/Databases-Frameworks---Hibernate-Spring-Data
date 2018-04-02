import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] phrases = {"Excellent product.",
        "Such a great product.", "I always use that product.",
        "Best product of its category.", "Exceptional product.", "I can’t live without this product."};

        String[] events = {"Now I feel good.", "I have succeeded with this product.",
        "Makes miracles. I am happy of the results!", "I cannot believe but now I feel awesome.",
        "Try it yourself, I am very satisfied.", "I feel great!"};

        String[] authors = {"Diana", "Petya", "Stella", "Elena", "Katya", "Iva", "Annie", "Eva"};

        String[] cities = {"Burgas", "Sofia", "Plovdiv", "Varna", "Ruse"};

        int count = Integer.parseInt(input.nextLine());
        for (int i = 0; i < count; i++) {
            Random random = new Random();
            String phrase = phrases[random.nextInt(phrases.length)];
            String event = events[random.nextInt(events.length)];
            String author = authors[random.nextInt(authors.length)];
            String city = cities[random.nextInt(cities.length)];

            System.out.println(phrase + " " + event + " " + author + " - " + city);
        }
    }
}
