package com.carbon.thephantasyrpg.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Creature class is an abstract class that represents a creature in the game. It contains the creature's basic
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class Creature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int level = 1;
    private int experience = 0;
    private int health = 50;
    private int mana = 25;
    private int stamina = 35;

    @Embedded
    private BasicAttributes basicAttributes;

    @Embedded
    private Body body;

    /**
     * Race of the creature.
     */
    @ManyToOne
    @JoinColumn(name = "race_id")
    private Race race;

    public int getFinalStrength() {
        return basicAttributes.getStrength() + (race != null ? race.getBasicAttributeModifiers().getStrengthModifier() : 0);
    }

    public int getFinalDexterity() {
        return basicAttributes.getDexterity() + (race != null ? race.getBasicAttributeModifiers().getDexterityModifier() : 0);
    }

    public int getFinalConstitution() {
        return basicAttributes.getConstitution() + (race != null ? race.getBasicAttributeModifiers().getConstitutionModifier() : 0);
    }

    public int getFinalIntelligence() {
        return basicAttributes.getIntelligence() + (race != null ? race.getBasicAttributeModifiers().getIntelligenceModifier() : 0);
    }

    public int getFinalWisdom() {
        return basicAttributes.getWisdom() + (race != null ? race.getBasicAttributeModifiers().getWisdomModifier() : 0);
    }

    public int getFinalCharisma() {
        return basicAttributes.getCharisma() + (race != null ? race.getBasicAttributeModifiers().getCharismaModifier() : 0);
    }

    public int getFinalHealth() {
        return getHealth() + getFinalConstitution() * 10 + getFinalStrength() * 2;
    }

    public int getFinalMana() {
        return getMana() + getFinalIntelligence() * 10 + getFinalWisdom() * 2;
    }

    public int getFinalStamina() {
        return getStamina() + getFinalDexterity() * 10 + getFinalConstitution() * 2;
    }
}
