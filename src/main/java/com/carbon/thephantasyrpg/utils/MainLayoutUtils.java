package com.carbon.thephantasyrpg.utils;

import com.carbon.thephantasyrpg.enums.I18N.MainLayoutViewI18N;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * The main layout utils
 */
@Slf4j
@Component
public class MainLayoutUtils {

    // The message utils
    private final MessageUtils messageUtils;

    /**
     * Constructor
     *
     * @param messageUtils the message utils
     */
    public MainLayoutUtils(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    /**
     * Get the message from the message key
     *
     * @param messageEnum the message key
     * @return the message
     */
    public String getMessage(MainLayoutViewI18N messageEnum) {
        log.info("Getting message from message key: {}", messageEnum.getMessageKey());
        return messageUtils.getMessage(messageEnum.getMessageKey());
    }
}
