package com.carbon.thephantasyrpg.repository;

import com.carbon.thephantasyrpg.model.Creature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Creature Repository
 */
@Repository
public interface CreatureRepository extends JpaRepository<Creature, Long> {
}