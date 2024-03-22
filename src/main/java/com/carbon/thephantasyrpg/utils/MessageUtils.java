package com.carbon.thephantasyrpg.utils;

import com.carbon.thephantasyrpg.enums.MessageKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@Component
public class MessageUtils {
    private final MessageSource messageSource;

    public MessageUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(MessageKey key) {
        try {
            return messageSource.getMessage(key.getMessageKey(), null, Locale.getDefault());
        } catch (NoSuchMessageException e) {
            log.warn("No message found for key '{}' for locale '{}'", key.getMessageKey(), Locale.getDefault(), e);
            // Return the key itself as a fallback
            return key.getMessageKey();
        }
    }

    // Optional: Overload getMessage to directly accept String keys
    public String getMessage(String key) {
        try {
            return messageSource.getMessage(key, null, Locale.getDefault());
        } catch (NoSuchMessageException e) {
            log.warn("No message found for key '{}' for locale '{}'", key, Locale.getDefault(), e);
            return key; // Fallback to the key itself
        }
    }
}
