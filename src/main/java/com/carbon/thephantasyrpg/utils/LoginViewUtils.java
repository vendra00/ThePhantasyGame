package com.carbon.thephantasyrpg.utils;

import com.carbon.thephantasyrpg.enums.I18N.LoginViewI18N;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginViewUtils {

    // The message source
    private final MessageUtils messageUtils;

    // Constructor
    public LoginViewUtils(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    // Get the message
    public String getMessage(LoginViewI18N messageEnum) {
        log.info("Getting message for: {}", messageEnum.getMessageKey());
        return messageUtils.getMessage(messageEnum.getMessageKey());
    }
}
