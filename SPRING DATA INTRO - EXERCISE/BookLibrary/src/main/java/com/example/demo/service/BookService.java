package com.example.demo.service;

import com.example.demo.entities.Book;
import com.example.demo.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
}
