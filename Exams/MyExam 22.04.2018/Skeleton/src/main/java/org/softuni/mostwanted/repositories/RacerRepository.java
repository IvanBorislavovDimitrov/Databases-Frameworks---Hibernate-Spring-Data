package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entities.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer> {

    Racer findByName(String name);
}
