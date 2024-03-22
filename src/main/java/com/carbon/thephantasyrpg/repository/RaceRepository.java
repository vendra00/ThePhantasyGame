package com.carbon.thephantasyrpg.repository;

import com.carbon.thephantasyrpg.model.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Race Repository
 */
@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {

    /**
     * Query to fetch basic attributes modifiers for a specific race in the game
     * */
    @Query("SELECT r.name, a.charismaModifier, a.constitutionModifier, a.dexterityModifier, a.intelligenceModifier, a.strengthModifier, a.wisdomModifier FROM Race r JOIN r.basicAttributeModifiers a")
    List<Object[]> findAllAttributes();
}

