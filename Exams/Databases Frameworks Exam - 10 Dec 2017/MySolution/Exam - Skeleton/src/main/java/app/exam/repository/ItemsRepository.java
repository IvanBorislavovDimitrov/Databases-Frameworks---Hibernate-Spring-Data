package app.exam.repository;

import app.exam.domain.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {

    Item findByName(String name);
}
