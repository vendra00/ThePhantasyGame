package com.carbon.thephantasyrpg.enums;

import lombok.Getter;

@Getter
public enum PlayerCreationViewI18N implements MessageKey {

    //Labels
    NAME_LABEL("nameField.label"),
    STRENGTH_LABEL("strengthField.label"),
    DEXTERITY_LABEL("dexterityField.label"),
    CONSTITUTION_LABEL("constitutionField.label"),
    INTELLIGENCE_LABEL("intelligenceField.label"),
    WISDOM_LABEL("wisdomField.label"),
    CHARISMA_LABEL("charismaField.label"),
    RACE_LABEL("raceField.label"),
    RACE_DESCRIPTION_LABEL("raceDescriptionField.label"),

    //Buttons
    CREATE_CHARACTER_BUTTON("createCharacter.button"),
    CLOSE_DIALOG_BUTTON("closeDialog.button"),
    ROLL_ATTRIBUTES_BONUS_BUTTON("rollAttributesBonus.button"),

    //Tooltips
    CHARACTER_BASIC_ATTRIBUTES_TOOLTIP("characterBasicAttribute.tooltip"),
    CHARACTER_BASIC_INFORMATION_TOOLTIP("characterBasicInformation.tooltip"),

    //Info Panel
    CHARACTER_BASIC_ATTRIBUTES_INFO_PANEL("characterBasicAttribute.infoPanel"),
    CHARACTER_BASIC_INFORMATION_INFO_PANEL("characterBasicInformation.infoPanel"),

    //Dialogs Messages
    CHARACTER_SUCCESS_DIALOG("characterCreationSuccess.dialog"),
    CHARACTER_FAIL_DIALOG("characterCreationFail.dialog"),

    // Frontend Messages
    FORM_ITEM_KEY("formFrontEnd.key"),

    //Error Messages
    NUMBER_FIELD_ERROR("numberField.error"),
    NAME_FIELD_MIN_LENGTH_ERROR("nameFieldMinLength.error"),
    NAME_FIELD_MAX_LENGTH_ERROR("nameFieldMaxLength.error"),
    NAME_FIELD_REQUIRED("nameFieldRequired.error");


    private final String messageKey;

    PlayerCreationViewI18N(String messageKey) {
        this.messageKey = messageKey;
    }

}
