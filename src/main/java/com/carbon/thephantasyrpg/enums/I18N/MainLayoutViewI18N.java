package com.carbon.thephantasyrpg.enums.I18N;

import lombok.Getter;

/**
 * Enum for the MainLayoutView I18N keys
 */
@Getter
public enum MainLayoutViewI18N implements MessageKey {

    // The message keys
    DASHBOARD_TITLE("dashboardTitle.label"),
    HOME_LABEL("homeRouterLink.label"),
    PLAY_GAME_LABEL("gameRouterLink.label"),
    CHARACTER_CREATION_LABEL("charCreationRouterLink.label"),
    SETTINGS_LABEL("settingsRouterLink.label"),
    LOGOUT_LABEL("logoutBtn.label")
    ;

    // The message key
    private final String messageKey;

    /**
     * Constructor
     * @param messageKey the message key
     */
    MainLayoutViewI18N(String messageKey) {
        this.messageKey = messageKey;
    }
}
