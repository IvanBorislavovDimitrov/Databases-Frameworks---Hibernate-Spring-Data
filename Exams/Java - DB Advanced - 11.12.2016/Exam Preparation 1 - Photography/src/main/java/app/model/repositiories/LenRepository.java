package app.model.repositiories;

import app.model.entities.Len;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LenRepository extends JpaRepository<Len, Long> {

    Len findFirstById(Long id);
}
