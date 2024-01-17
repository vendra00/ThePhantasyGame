package com.carbon.thephantasyrpg.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class BodySection {
    private int health;
    private boolean isWounded;
}
