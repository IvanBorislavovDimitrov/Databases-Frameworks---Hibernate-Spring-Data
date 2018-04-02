package com.example.demo.repositories;

import com.example.demo.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "select distinct a from Author a join a.books as b where b.releaseDate < '1990-12-31'")
    List<Author> getAllByAtLeastOneAndBook();

    @Query(value = "select distinct count(a.authorId), a.firstName, a.lastName" +
            " from Author a join a.books as b group by a.authorId order by count (a.authorId) desc")
    List<Object[]> getAllByCountOfBooks();
}
