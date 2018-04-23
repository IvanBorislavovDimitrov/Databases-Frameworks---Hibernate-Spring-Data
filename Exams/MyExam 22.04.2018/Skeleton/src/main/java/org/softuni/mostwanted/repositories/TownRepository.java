package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {

    Town findByName(String name);
}
