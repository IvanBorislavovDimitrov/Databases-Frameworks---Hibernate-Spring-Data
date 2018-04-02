package book_library;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        Library library = new Library("Library");

        int countOfLines = Integer.parseInt(input.readLine());

        for (int i = 0; i < countOfLines; i++) {
            String[] infoAboutLine = input.readLine().split("\\s+");
            String title = infoAboutLine[0];
            String authorName = infoAboutLine[1];
            String publisher = infoAboutLine[2];
            String releaseDate = infoAboutLine[3];
            String isbnNumber = infoAboutLine[4];
            double price = Double.parseDouble(infoAboutLine[5]);
            if (library.getBooks().stream().filter(x -> x.getAuthor().equals(authorName)).toArray().length > 0) {
                double oldPrice = library.getBooks()
                        .stream()
                        .filter(x -> x.getAuthor().equals(authorName))
                        .findFirst()
                        .get()
                        .getPrice();

                library.getBooks()
                        .stream()
                        .filter(x -> x.getAuthor().equals(authorName))
                        .findFirst()
                        .get()
                        .setPrice(oldPrice + price);
            } else {
                library.getBooks().add(new Book(title, authorName, publisher, releaseDate, isbnNumber, price));
            }
        }

        library.getBooks().sort(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if (Double.compare(o2.getPrice(), o1.getPrice()) == 0) {
                    return o1.getAuthor().compareTo(o2.getAuthor());
                }
                return Double.compare(o2.getPrice(), o1.getPrice());
            }
        });

        for (Book book : library.getBooks()) {
            System.out.println(String.format("%s -> %.2f", book.getAuthor(), book.getPrice()));
        }
    }
}
