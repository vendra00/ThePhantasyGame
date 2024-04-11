package com.carbon.thephantasyrpg.utils;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.enums.PlayerCreationViewI18N;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Utility class to get localized messages for the PlayerCreationView
 */
@Slf4j
@Component
public class PlayerCreationViewUtils {

    // The message source
    private final MessageUtils messageUtils;

    /**
     * Constructor for the PlayerCreationViewUtils
     * @param messageUtils the message source
     */
    public PlayerCreationViewUtils(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    public String getMessage(PlayerCreationViewI18N messageEnum) {
        return messageUtils.getMessage(messageEnum.getMessageKey());
    }

    /**
     * Bind a text field to a string field in the binder
     * @param binder the binder
     * @param field the text field
     * @param getter the getter for the string field
     * @param setter the setter for the string field
     */
    public void basicAttributesFieldValidator(Binder<PlayerCreationDTO> binder, NumberField field,
                                              ValueProvider<PlayerCreationDTO, Integer> getter,
                                              Setter<PlayerCreationDTO, Integer> setter) {
        binder.forField(field)
                .withConverter(new DoubleToIntegerConverter(
                        messageUtils.getMessage(PlayerCreationViewI18N.NUMBER_FIELD_ERROR)))
                .bind(getter, setter);
    }
}
