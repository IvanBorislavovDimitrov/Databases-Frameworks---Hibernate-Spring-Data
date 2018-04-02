package com.example.demo.repositories;

import com.example.demo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getAllByReleaseDateAfter(Date date);

    @Query(value = "select b from Book b join b.author as a where CONCAT(a.firstName, ' ', a.lastName) =" +
            "'George Powell' order by b.releaseDate desc , b.title asc")
    List<Book> getAllByAuthor();
}
