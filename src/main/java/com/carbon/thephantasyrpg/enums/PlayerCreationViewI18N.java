package com.carbon.thephantasyrpg.enums;

import lombok.Getter;

@Getter
public enum PlayerCreationViewI18N {
    //Labels
    NAME_LABEL("nameField.label"),
    STRENGTH_LABEL("strengthField.label"),
    DEXTERITY_LABEL("dexterityField.label"),
    CONSTITUTION_LABEL("constitutionField.label"),
    INTELLIGENCE_LABEL("intelligenceField.label"),
    WISDOM_LABEL("wisdomField.label"),
    CHARISMA_LABEL("charismaField.label"),
    RACE_LABEL("raceField.label"),

    //Buttons
    CREATE_CHARACTER_BUTTON("createCharacter.button"),

    //Tooltips
    CHARACTER_BASIC_ATTRIBUTES_TOOLTIP("characterBasicAttribute.tooltip"),
    CHARACTER_BASIC_INFORMATION_TOOLTIP("characterBasicInformation.tooltip"),

    //Info Panel
    CHARACTER_BASIC_ATTRIBUTES_INFO_PANEL("characterBasicAttribute.infoPanel"),
    CHARACTER_BASIC_INFORMATION_INFO_PANEL("characterBasicInformation.infoPanel"),

    //Error Messages
    NUMBER_FIELD_ERROR("numberField.error"),
    NAME_FIELD_MIN_LENGTH_ERROR("nameFieldMinLength.error"),
    NAME_FIELD_MAX_LENGTH_ERROR("nameFieldMaxLength.error"),
    NAME_FIELD_REQUIRED("nameFieldRequired.error");




    private final String message;

    PlayerCreationViewI18N(String message) {
        this.message = message;
    }

}
