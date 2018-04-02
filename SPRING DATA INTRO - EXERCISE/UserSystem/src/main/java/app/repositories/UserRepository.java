package app.repositories;

import app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    @Transactional
    @Modifying
    @Query(value = "update User u set u.isDeleted = 1 where u.lastTimeLoggedIn < :date")
    void removeInactiveUsers(@Param("date") Timestamp date);

    @Transactional
    @Modifying
    void deleteAllByIsDeleted(int isDeleted);

}
