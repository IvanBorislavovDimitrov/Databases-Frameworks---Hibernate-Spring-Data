package app.model.repositiories;

import app.model.entities.BasicCamera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicCameraRepository extends JpaRepository<BasicCamera, Long> {

    @Query("select count(bc.id) from BasicCamera bc")
    long count();

    BasicCamera findFirstBy(Long id);
}
