package com.example.demo.repositories;

import com.example.demo.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedStoredProcedureQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "select distinct a from Author a join a.books as b where b.releaseDate < '1990-12-31'")
    List<Author> getAllByAtLeastOneAndBook();

    @Query(value = "select distinct count(a.authorId), a.firstName, a.lastName" +
            " from Author a join a.books as b group by a.authorId order by count (a.authorId) desc")
    List<Object[]> getAllByCountOfBooks();

    List<Author> findAllByFirstNameEndingWith(String end);


    @Query(value = "select b, a from Author a join a.books b where a.lastName like(concat(:start, '%'))")
    List<Object[]> getBooksWithAuthorsStartingWith(@Param("start") String starts);

    @Query(value = "select a, sum(b.copies) from Author a join a.books b group by a.authorId")
    List<Object[]> getCountOfAuthorBooks();

}
