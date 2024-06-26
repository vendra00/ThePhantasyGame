package com.carbon.thephantasyrpg.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

/**
 * The RaceBasicAttributeModifiers class is an embeddable class that holds the basic attribute modifiers
 */
@Getter
@Setter
@Embeddable
public class RaceBasicAttributeModifiers {
    private int strengthModifier;
    private int dexterityModifier;
    private int constitutionModifier;
    private int intelligenceModifier;
    private int wisdomModifier;
    private int charismaModifier;
}
