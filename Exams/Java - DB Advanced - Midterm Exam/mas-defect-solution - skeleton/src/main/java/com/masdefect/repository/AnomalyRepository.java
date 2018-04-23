package com.masdefect.repository;

import com.masdefect.domain.entities.Anomaly;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly, Integer> {

    @Query("select a from Anomaly a join a.people p group by a.id order by count(p.id)")
    List<Anomaly> getAnomaliesByCount();
}
