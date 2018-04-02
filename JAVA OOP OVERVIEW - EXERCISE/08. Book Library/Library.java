package book_library;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private String name;
    private List<Book> books;

    public Library(String name) {
        this.setName(name);
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
