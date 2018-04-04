package app.repositories;

import app.models.entities.Game;
import app.models.entities.User;
import app.models.entities.dtos.view.UserFullNameIdIsAdministrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    User getById(Long id);

    @Query("select r.id from User u join u.roles r where u.id = :user_id")
    int getRole(@Param("user_id") Long id);

    User getUserById(Long id);


}
