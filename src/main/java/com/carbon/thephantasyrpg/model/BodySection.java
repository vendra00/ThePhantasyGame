package com.carbon.thephantasyrpg.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * The BodySection class is an embed of the Body class that represents a section of a creature's body.
 * It contains the health of the section and whether it is wounded.
 */
@Getter
@Setter
@Embeddable
public class BodySection {
    private int health;
    private boolean isWounded;
}
