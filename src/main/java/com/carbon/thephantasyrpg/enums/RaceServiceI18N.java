package com.carbon.thephantasyrpg.enums;

import lombok.Getter;

@Getter
public enum RaceServiceI18N implements MessageKey {

    CHARISMA_MODIFIER("charismaModifier.name"),
    CONSTITUTION_MODIFIER("constitutionModifier.name"),
    STRENGTH_MODIFIER("strengthModifier.name"),
    DEXTERITY_MODIFIER("dexterityModifier.name"),
    INTELLIGENCE_MODIFIER("intelligenceModifier.name"),
    WISDOM_MODIFIER("wisdomModifier.name"),

    //Error Messages
    CHARISMA_MODIFIER_IS_NULL("charismaNullField.error"),
    CONSTITUTION_MODIFIER_IS_NULL("constitutionNullField.error"),
    STRENGTH_MODIFIER_IS_NULL("strengthNullField.error"),
    DEXTERITY_MODIFIER_IS_NULL("dexterityNullField.error"),
    INTELLIGENCE_MODIFIER_IS_NULL("intelligenceNullField.error"),
    WISDOM_MODIFIER_IS_NULL("wisdomNullField.error");

    private final String messageKey;

    RaceServiceI18N(String messageKey) {
        this.messageKey = messageKey;
    }

}
