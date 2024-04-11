package com.carbon.thephantasyrpg.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Player class is an entity class that represents a player in the database
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Player extends Creature {

    /**
     * The user that the player belongs to
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
