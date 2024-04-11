package com.carbon.thephantasyrpg.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * The Perk class is an embeddable class that holds the name, description, and whether the perk is positive or negative
 */
@Getter
@Setter
@Embeddable
public class Perk {
    private String name;
    private String description;
    private boolean isPositive;
}

