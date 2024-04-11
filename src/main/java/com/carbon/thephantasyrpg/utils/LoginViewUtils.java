package com.carbon.thephantasyrpg.utils;

import com.carbon.thephantasyrpg.enums.I18N.LoginViewI18N;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginViewUtils {
    // The message source
    private final MessageUtils messageUtils;

    public LoginViewUtils(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    public String getMessage(LoginViewI18N messageEnum) {
        return messageUtils.getMessage(messageEnum.getMessageKey());
    }
}
