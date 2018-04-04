package app.repositories;

import app.models.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    void deleteById(Long id);

    List<Game> findByTitle(String title);

    Game getByTitle(String title);
}
