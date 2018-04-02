package com.example.demo;

import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.entities.Category;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
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
        initialize();
        showBooksAfter2000();
        showAuthorsWithAtLeastOneBookBefore1990();
        getAllAuthorsByCountOfTheirBooks();
        getAllByAuthor();
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
            int bookId = new Random().nextInt(books.size());
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
