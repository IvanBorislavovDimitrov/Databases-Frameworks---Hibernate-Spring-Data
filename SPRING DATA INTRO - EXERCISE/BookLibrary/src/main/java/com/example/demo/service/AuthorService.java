package com.example.demo.service;


import com.example.demo.entities.Author;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void register(Author author) {
        this.authorRepository.saveAndFlush(author);
    }

    public Author getAuthorById(Long id) {
        return this.authorRepository.findById(id).get();
    }

    public List<Author> getAllByAtLeastOneAndBook() {
        return this.authorRepository.getAllByAtLeastOneAndBook();
    }

    public List<Object[]> getAllByCountOfBooks() {
        return this.authorRepository.getAllByCountOfBooks();
    }

}
