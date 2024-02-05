package com.carbon.thephantasyrpg.utils;

import com.carbon.thephantasyrpg.enums.PlayerCreationViewI18N;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Utility class to get localized messages for the PlayerCreationView
 */
@Component
public class PlayerCreationViewUtils {
    private final MessageSource messageSource;

    /**
     * Constructor for the PlayerCreationViewUtils
     * @param messageSource the message source
     */
    public PlayerCreationViewUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Get the localized message for the given message enum
     * @param messageEnum the message enum
     * @return the localized message
     */
    public String getMessage(PlayerCreationViewI18N messageEnum) {
        return messageSource.getMessage(messageEnum.getMessage(), null, Locale.getDefault());
    }
}
