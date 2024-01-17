package com.carbon.thephantasyrpg.utils;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.model.BasicAttributes;
import com.carbon.thephantasyrpg.model.Player;

public class PlayerServiceUtils {
    public static void calculatePlayerAttributes(PlayerCreationDTO playerCreationDTO, Player player) {
        player.setBasicAttributes(new BasicAttributes());
        player.getBasicAttributes().setStrength(playerCreationDTO.getStrength() + player.getRace().getBasicAttributeModifiers().getStrengthModifier());
        player.getBasicAttributes().setDexterity(playerCreationDTO.getDexterity() + player.getRace().getBasicAttributeModifiers().getDexterityModifier());
        player.getBasicAttributes().setConstitution(playerCreationDTO.getConstitution() + player.getRace().getBasicAttributeModifiers().getConstitutionModifier());
        player.getBasicAttributes().setIntelligence(playerCreationDTO.getIntelligence() + player.getRace().getBasicAttributeModifiers().getIntelligenceModifier());
        player.getBasicAttributes().setWisdom(playerCreationDTO.getWisdom() + player.getRace().getBasicAttributeModifiers().getWisdomModifier());
        player.getBasicAttributes().setCharisma(playerCreationDTO.getCharisma() + player.getRace().getBasicAttributeModifiers().getCharismaModifier());
    }

     public static void calculatePlayerHealManaStamina(Player player) {
        player.setHealth(player.getHealth() + player.getBasicAttributes().getConstitution() * 10 + player.getBasicAttributes().getStrength() * 2);
        player.setMana(player.getMana() + player.getBasicAttributes().getIntelligence() * 2 + player.getBasicAttributes().getWisdom() * 2);
        player.setStamina(player.getStamina() + player.getBasicAttributes().getStrength() * 2 + player.getBasicAttributes().getDexterity() * 2);
    }
}
