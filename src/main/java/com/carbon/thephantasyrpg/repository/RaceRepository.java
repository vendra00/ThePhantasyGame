package com.carbon.thephantasyrpg.repository;

import com.carbon.thephantasyrpg.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
}

