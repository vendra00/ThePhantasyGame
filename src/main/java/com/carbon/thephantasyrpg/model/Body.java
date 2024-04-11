package com.carbon.thephantasyrpg.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * The Body class is an embeddable class that represents the body of a creature in the game. It contains the creature's
 * body parts and their respective sections.
 */
@Setter
@Getter
@Embeddable
public class Body {
    @ElementCollection
    private List<BodySection> heads = new ArrayList<>();

    @ElementCollection
    private List<BodySection> torsos = new ArrayList<>();

    @ElementCollection
    private List<BodySection> arms = new ArrayList<>();

    @ElementCollection
    private List<BodySection> legs = new ArrayList<>();
}
