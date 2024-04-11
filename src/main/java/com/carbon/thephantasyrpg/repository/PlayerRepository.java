package com.carbon.thephantasyrpg.repository;

import com.carbon.thephantasyrpg.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Player Repository
 */
@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
