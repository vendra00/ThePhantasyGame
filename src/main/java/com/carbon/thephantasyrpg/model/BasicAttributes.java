package com.carbon.thephantasyrpg.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * The BasicAttributes class is an embeddable class that represents the basic attributes of a creature in the game.
 * It contains the creature's strength, dexterity, constitution, intelligence, wisdom, and charisma.
 */
@Getter
@Setter
@Embeddable
public class BasicAttributes {
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
}
