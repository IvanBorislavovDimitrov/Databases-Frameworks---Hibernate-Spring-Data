package com.masdefect.repository;

import com.masdefect.domain.entities.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer> {

    Planet findByName(String name);

    @Query("select p from Planet as p where p not in (select a.teleportPlanet from Anomaly as a)")
    List<Planet> planetNotInTeleports();
}
