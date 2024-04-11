package com.carbon.thephantasyrpg.enums.I18N;

import lombok.Getter;

/**
 * Enum containing the message keys for the Login view.
 */
@Getter
public enum LoginViewI18N implements MessageKey {

    ROUTER_LINK("registerLink.label"),
    LOGIN_TITLE("loginTitle.label");

    private final String messageKey;

    /**
     * Constructor.
     *
     * @param messageKey the message key
     */
    LoginViewI18N(String messageKey) {
        this.messageKey = messageKey;
    }
}
