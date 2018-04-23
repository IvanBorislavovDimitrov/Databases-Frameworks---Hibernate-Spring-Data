package app.model.repositiories;

import app.model.entities.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographerRepository extends JpaRepository<Photographer, Long> {

    @Query("select count(p.id) from Photographer p")
    long count();

    Photographer findFirstByFirstNameAndLastName(String firstName, String lastName);
}
