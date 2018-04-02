package com.example.demo.service;

import com.example.demo.dto.ReducedBook;
import com.example.demo.entities.Author;
import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Primary
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void register(Book book) {
        this.bookRepository.saveAndFlush(book);
    }

    public List<Book> getAllByReleaseDateAfter(Date date) {
        return this.bookRepository.getAllByReleaseDateAfter(date);
    }

    public List<Book> getAllByAuthor() {
        return this.bookRepository.getAllByAuthor();
    }

    public List<Book> findAllByAgeRestriction(String ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(ageRestriction);
    }

    public List<Book> findAllByEditionTypeAndCopies(String editionType, int copies) {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(editionType, copies);
    }

    public List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal priceLow, BigDecimal priceGreat) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(priceLow, priceGreat);
    }


    public List<Book> findAllByReleaseDateIsBeforeAndReleaseDateIsAfter(Date earlyDate, Date lateDate) {
        return this.bookRepository.findAllByReleaseDateIsBeforeAndReleaseDateIsAfter(earlyDate, lateDate);
    }

    public List<Book> findAllByReleaseDateIsBefore(Date date) {
        return this.bookRepository.findAllByReleaseDateIsBefore(date);
    }

    public List<Book> findAllByTitleContainingIgnoreCase(String cnt) {
        return this.bookRepository.findAllByTitleContainingIgnoreCase(cnt);
    }

    public int countOfBooksWithTitleLengthGreaterThan(int size) {
        List<Book> books = this.bookRepository.findAll();
        int count = 0;
        for (Book book : books) {
            if (book.getTitle().length() > size) {
                count++;
            }
        }

        return count;
    }

    public List<ReducedBook> getReducedBooks(String title) {
        List<Book> booksWithTitle = this.bookRepository.findAllByTitle(title);
        List<ReducedBook> reducedBooks = new ArrayList<>();
        for (Book book : booksWithTitle) {
            ReducedBook reducedBook = new ReducedBook(book.getTitle(),
                    book.getEditionType(), book.getAgeRestriction(), book.getPrice());
            reducedBooks.add(reducedBook);
        }

        return reducedBooks;
    }

    public int increaseBookCopies(String dateString, int number) throws ParseException {
        Date date = new SimpleDateFormat("dd MMM yyyy").parse(dateString);
        return this.bookRepository.increaseBookCopies(date, number);
    }

    public int deleteAllByCopiesLessThan(int count) {
        return this.bookRepository.deleteAllByCopiesLessThan(count);
    }


    public Integer getTotalBooksOfAuthor(String first_name, String last_name) {
        return this.bookRepository.getTotalBooksOfAuthor(first_name, last_name);
    }


}
