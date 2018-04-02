package com.example.demo.service;


import com.example.demo.entities.Author;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    public List<Author> findAllByFirstNameEndingWith(String end) {
        return this.authorRepository.findAllByFirstNameEndingWith(end);
    }

    public List<Object[]> getBooksWithAuthorsStartingWith(@Param("start") String starts) {
        return this.authorRepository.getBooksWithAuthorsStartingWith(starts);
    }

    public String[] countOfAuthorBooks() {
        List<Object[]> authorBooks = this.authorRepository.getCountOfAuthorBooks();
        String[] arr = new String[authorBooks.size()];
        int count = 0;
        for (Object[] objArr : authorBooks) {
            Author author = (Author) objArr[0];
            long totalCopies = (long) objArr[1];
            arr[count++] = author.getFirstName() + " " + author.getLastName() + " - " + totalCopies;
         }

         return Arrays.stream(arr).sorted((x1, x2) -> {
             long count1 = Long.parseLong(x1.split(" - ")[1]);
             long count2 = Long.parseLong(x2.split(" - ")[1]);

             return Long.compare(count2, count1);
         }).toArray(String[]::new);
    }

}
