package com.masdefect.repository;

import com.masdefect.domain.entities.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends JpaRepository<Star, Integer> {

    Star findByName(String name);
}
