package com.example.demo;

import com.example.demo.dto.ReducedBook;
import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.entities.Category;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@Component
@Transactional
public class ConsoleRunner implements CommandLineRunner {

    private static final String AUTHORS_FILE = "C:\\Users\\Ivan\\Downloads\\Skeleton\\folder\\SoftUni_Databases_Frameworks_Hibernate_-_Spring_Data\\8.1 Spring Data Intro\\demo\\src\\main\\resources\\authors.txt";
    private static final String BOOKS_FILE = "C:\\Users\\Ivan\\Downloads\\Skeleton\\folder\\SoftUni_Databases_Frameworks_Hibernate_-_Spring_Data\\8.1 Spring Data Intro\\demo\\src\\main\\resources\\books.txt";
    private static final String CATEGORIES_FILE = "C:\\Users\\Ivan\\Downloads\\Skeleton\\folder\\SoftUni_Databases_Frameworks_Hibernate_-_Spring_Data\\8.1 Spring Data Intro\\demo\\src\\main\\resources\\categories.txt";

    private AuthorService authorService;
    private BookService bookService;
    private CategoryService categoryService;

    @Autowired
    public ConsoleRunner(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
//        initialize();
//        showBooksAfter2000();
//        showAuthorsWithAtLeastOneBookBefore1990();
//        getAllAuthorsByCountOfTheirBooks();
//        getAllByAuthor();

//        bookTitleByAgeRestriction();
//        goldenBooks();
//        booksByPrice();
//        notReleasedBooks();
//        booksReleaseBeforeDate();
//        authorSearch();
//        bookSearch();
//        bookTitleSearch();
//        countBooks();
//        totalBookCopies();
//        reducedBook();
//        increaseBookCopies();
//        removeBooks();

//        getAuthorsAndTheirBooks();
    }

    private void getAuthorsAndTheirBooks() {
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();
        String firstName = name.split("\\s+")[0];
        String lastName = name.split("\\s+")[1];

        int countOfBooks = this.bookService.getTotalBooksOfAuthor(firstName, lastName);

        if (countOfBooks > 1) {
            System.out.println(String.format("%s %s has written %d books", firstName, lastName, countOfBooks));
        } else if (countOfBooks == 1) {
            System.out.println(String.format("%s %s has written %d book", firstName, lastName, countOfBooks));
        } else {
            System.out.println(String.format("%s %s has not written any books yet", firstName, lastName));
        }
    }

    private void removeBooks() {
        Scanner input = new Scanner(System.in);
        int count = Integer.parseInt(input.nextLine());
        System.out.println(String.format("%d books are deleted", this.bookService.deleteAllByCopiesLessThan(count)));
    }

    private void increaseBookCopies() throws ParseException {
        Scanner input = new Scanner(System.in);
        String date = input.nextLine();
        int number = Integer.parseInt(input.nextLine());

        System.out.println(this.bookService.increaseBookCopies(date, number));
    }

    private void reducedBook() {
        String title = new Scanner(System.in).nextLine();
        List<ReducedBook> reducedBooks = this.bookService.getReducedBooks(title);
        for (ReducedBook reducedBook : reducedBooks) {
            System.out.println(reducedBook);
        }
    }

    private void totalBookCopies() {
        Scanner input = new Scanner(System.in);
        String[] arr = this.authorService.countOfAuthorBooks();
        for (String str : arr) {
            System.out.println(str);
        }
    }

    private void countBooks() {
        Scanner input = new Scanner(System.in);
        int size = Integer.parseInt(input.nextLine());
        System.out.println(this.bookService.countOfBooksWithTitleLengthGreaterThan(size));
    }

    private void bookTitleSearch() {
        String start = new Scanner(System.in).nextLine();
        for (Object[] bookAuthor : this.authorService.getBooksWithAuthorsStartingWith(start)) {
            System.out.println(((Book) bookAuthor[0]).getTitle() + " (" + ((Author) bookAuthor[1]).getFirstName() + " " +
                    ((Author) bookAuthor[1]).getLastName() + ")");
        }
    }

    private void bookSearch() {
        String cont = new Scanner(System.in).nextLine().toLowerCase();
        for (Book book : this.bookService.findAllByTitleContainingIgnoreCase(cont)) {
            System.out.println(book.getTitle());
        }
    }

    private void authorSearch() {
        String end = new Scanner(System.in).nextLine();
        for (Author author : this.authorService.findAllByFirstNameEndingWith(end)) {
            System.out.println(String.format("%s %s", author.getFirstName(), author.getLastName()));
        }
    }

    private void booksReleaseBeforeDate() {
        Scanner input = new Scanner(System.in);
        String date = input.nextLine();
        String[] dateInfo = date.split("-");
        int day = Integer.parseInt(dateInfo[0]);
        int month = Integer.parseInt(dateInfo[1]);
        int year = Integer.parseInt(dateInfo[2]);
        Date dateBefore = new Date(year, month, day);
        for (Book book : this.bookService.findAllByReleaseDateIsBefore(dateBefore)) {
            System.out.println(book.getTitle() + " " + book.getEditionType() + " $" + book.getPrice());
        }
    }

    private void notReleasedBooks() {
        Scanner input = new Scanner(System.in);
        int year = Integer.parseInt(input.nextLine());
        Date earlyDate = new Date(year, 1, 1);
        Date lateDate = new Date(year, 12, 31);
        for (Book book : this.bookService.findAllByReleaseDateIsBeforeAndReleaseDateIsAfter(earlyDate, lateDate)) {
            System.out.println(book.getTitle());
        }
    }

    private void booksByPrice() {
        Scanner input = new Scanner(System.in);
        BigDecimal priceLow = new BigDecimal(5);
        BigDecimal priceHigh  = new BigDecimal(40);

        for (Book book : this.bookService.findAllByPriceLessThanOrPriceGreaterThan(priceLow, priceHigh)) {
            System.out.println(String.format("%s - $%s", book.getTitle(), book.getPrice()));
        }
    }

    private void goldenBooks() {
        for (Book book : this.bookService.findAllByEditionTypeAndCopies("Gold", 5000)) {
            System.out.println(book.getTitle());
        }
    }

    private void bookTitleByAgeRestriction() {
        String ageRestriction = new Scanner(System.in).nextLine().toLowerCase();
        ageRestriction = String.valueOf(ageRestriction.charAt(0)).toUpperCase() + ageRestriction.substring(1);
        for (Book book : this.bookService.findAllByAgeRestriction(ageRestriction)) {
            System.out.println(book.getTitle());
        }
    }

    private void getAllByAuthor() {
        System.out.println("----------------------------------");
        for (Book book : this.bookService.getAllByAuthor()) {
            System.out.println(String.format("%s %s %s", book.getTitle(), book.getReleaseDate(), book.getCopies()));
        }
        System.out.println("----------------------------------");
    }

    private void getAllAuthorsByCountOfTheirBooks() {
        System.out.println("----------------------------------");
        for (Object[] obj : this.authorService.getAllByCountOfBooks()) {
            System.out.println(obj[0] + " " + obj[1] + " " + obj[2]);
        }
        System.out.println("----------------------------------");
    }

    private void showAuthorsWithAtLeastOneBookBefore1990() {
        System.out.println("----------------------------------");
        for (Author author : this.authorService.getAllByAtLeastOneAndBook()) {
            System.out.println(author.getFirstName() + " " + author.getLastName());
        }
        System.out.println("----------------------------------");
    }

    private void showBooksAfter2000() {
        System.out.println("----------------------------------");
        System.out.println("All books after 2000");
        for (Book book : this.bookService.getAllByReleaseDateAfter(new Date("31/12/2000"))) {
            System.out.println(book.getTitle());
        }
        System.out.println("----------------------------------");
    }

    private void initialize() throws IOException {
        List<Author> authors = saveAndGetAllAuthors();
        List<Book> books = saveAndGetAllBooks(authors);
        List<Category> categories = saveAndGetAllCategories(books);
        fillBooksCategories(books, categories);
    }

    private void fillBooksCategories(List<Book> books, List<Category> categories) {
        for (Book book : books) {
            Category category = categories.get(new Random().nextInt(categories.size()));
            book.getCategories().add(category);
            category.getBooks().add(book);
            this.bookService.register(book);
        }
    }

    private List<Category> saveAndGetAllCategories(List<Book> books) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(CATEGORIES_FILE));
        List<Category> categories = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().equals("")) {
                continue;
            }
            Category category = new Category();
            category.setName(line);
            categories.add(category);
            this.categoryService.register(category);
        }

        return categories;
    }

    private List<Book> saveAndGetAllBooks(List<Author> authors) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE));

        List<Book> books = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] bookInfo = line.split("\\s+");
            int editionType = Integer.parseInt(bookInfo[0]);
            String editionTypeString = getEditionTypeString(editionType);
            String releaseDateString = bookInfo[1];
            Date releaseDate = new Date(releaseDateString);
            int copies = Integer.parseInt(bookInfo[2]);
            BigDecimal price = new BigDecimal(bookInfo[3]);
            int ageRestriction = Integer.parseInt(bookInfo[4]);
            String ageRestrictionString = getAgeRestrictionString(ageRestriction);
            String title = bookInfo[5];
            Book book = new Book();
            book.setEditionType(editionTypeString);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestrictionString);
            book.setTitle(title);

            int authorId = new Random().nextInt(authors.size());

            authors.get(authorId).getBooks().add(book);
            book.setAuthor(authors.get(authorId));
            books.add(book);
            this.bookService.register(book);
        }

        return books;
    }

    private List<Author> saveAndGetAllAuthors() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(AUTHORS_FILE));

        List<Author> authors = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            Author author = new Author();
            String[] authorInfo =  line.split("\\s+");
            String firstName = authorInfo[0];
            String lastName = authorInfo[1];
            author.setFirstName(firstName);
            author.setLastName(lastName);
            authors.add(author);
            authorService.register(author);
        }

        return authors;
    }

    private String getAgeRestrictionString(int restrictionType) {
        switch (restrictionType) {
            case 0:
                return "Minor";
            case 1:
                return "Teen";
            case 2:
                return "Adult";
        }

        return null;
    }

    private String getEditionTypeString(int editionType) {
        switch (editionType) {
            case 0:
                return "Normal";
            case 1:
                return "Promo";
            case 2:
                return "Gold";
        }

        return null;
    }
}
