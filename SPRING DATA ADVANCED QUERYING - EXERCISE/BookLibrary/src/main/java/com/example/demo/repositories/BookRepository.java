package com.example.demo.repositories;

import com.example.demo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getAllByReleaseDateAfter(Date date);

    @Query(value = "select b from Book b join b.author as a where CONCAT(a.firstName, ' ', a.lastName) =" +
            "'George Powell' order by b.releaseDate desc , b.title asc")
    List<Book> getAllByAuthor();

    List<Book> findAllByAgeRestriction(String ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(String editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal priceLow, BigDecimal priceGreat);

    @Query(value = "select b from Book b where b.releaseDate not between :earlyDate and :lateDate")
    List<Book> findAllByReleaseDateIsBeforeAndReleaseDateIsAfter(@Param("earlyDate") Date earlyDate, @Param("lateDate") Date lateDate);

    List<Book> findAllByReleaseDateIsBefore(Date date);

    List<Book> findAllByTitleContainingIgnoreCase(String cnt);

    List<Book> findAllByTitle(String title);

    @Query(value = "update Book b set b.copies = b.copies + :number where b.releaseDate > :dateS")
    @Modifying
    @Transactional
    int increaseBookCopies(@Param("dateS") Date date, @Param("number") int number);

    int deleteAllByCopiesLessThan(int count);

    @Procedure(name = "usp_total_books")
    Integer getTotalBooksOfAuthor(@Param("first_name") String first_name, @Param("last_name")String last_name);
}
