package com.carbon.thephantasyrpg.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The NPC class is an entity class that represents a non-player character in the database
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
public class NPC extends Creature {
    // Additional fields specific to NPC
}
