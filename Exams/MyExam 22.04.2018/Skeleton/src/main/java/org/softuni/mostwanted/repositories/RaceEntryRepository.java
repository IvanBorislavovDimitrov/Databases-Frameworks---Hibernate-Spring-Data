package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entities.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceEntryRepository extends JpaRepository<RaceEntry, Integer> {
}
