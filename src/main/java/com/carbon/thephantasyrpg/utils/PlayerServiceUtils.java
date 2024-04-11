package com.carbon.thephantasyrpg.utils;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.model.BasicAttributes;
import com.carbon.thephantasyrpg.model.Player;

/**
 *  Utility class for player service
 */
public class PlayerServiceUtils {

    // Constants for calculating player attributes
    private static final int HEALTH_CONSTITUTION_MULTIPLIER = 10;
    private static final int HEALTH_STRENGTH_MULTIPLIER = 2;
    private static final int MANA_INTELLIGENCE_MULTIPLIER = 2;
    private static final int MANA_WISDOM_MULTIPLIER = 2;
    private static final int STAMINA_STRENGTH_MULTIPLIER = 2;
    private static final int STAMINA_DEXTERITY_MULTIPLIER = 2;

    /**
     * Calculate player attributes based on character creation
     * @param playerCreationDTO data transfer object for player creation
     * @param player player object
     */
    public static void calculatePlayerAttributes(PlayerCreationDTO playerCreationDTO, Player player) {
        player.setBasicAttributes(new BasicAttributes());
        player.getBasicAttributes().setStrength(playerCreationDTO.getStrength() + player.getRace().getBasicAttributeModifiers().getStrengthModifier());
        player.getBasicAttributes().setDexterity(playerCreationDTO.getDexterity() + player.getRace().getBasicAttributeModifiers().getDexterityModifier());
        player.getBasicAttributes().setConstitution(playerCreationDTO.getConstitution() + player.getRace().getBasicAttributeModifiers().getConstitutionModifier());
        player.getBasicAttributes().setIntelligence(playerCreationDTO.getIntelligence() + player.getRace().getBasicAttributeModifiers().getIntelligenceModifier());
        player.getBasicAttributes().setWisdom(playerCreationDTO.getWisdom() + player.getRace().getBasicAttributeModifiers().getWisdomModifier());
        player.getBasicAttributes().setCharisma(playerCreationDTO.getCharisma() + player.getRace().getBasicAttributeModifiers().getCharismaModifier());
    }

    /**
     * Calculate player health, mana, and stamina based on basic attributes
     * @param player player object
     */
     public static void calculatePlayerHealManaStamina(Player player) {
        player.setHealth(player.getHealth() + player.getBasicAttributes().getConstitution() * HEALTH_CONSTITUTION_MULTIPLIER + player.getBasicAttributes().getStrength() * HEALTH_STRENGTH_MULTIPLIER);
        player.setMana(player.getMana() + player.getBasicAttributes().getIntelligence() * MANA_INTELLIGENCE_MULTIPLIER + player.getBasicAttributes().getWisdom() * MANA_WISDOM_MULTIPLIER);
        player.setStamina(player.getStamina() + player.getBasicAttributes().getStrength() * STAMINA_STRENGTH_MULTIPLIER + player.getBasicAttributes().getDexterity() * STAMINA_DEXTERITY_MULTIPLIER);
    }
}
