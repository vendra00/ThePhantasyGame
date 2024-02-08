package com.carbon.thephantasyrpg.utils;

import com.carbon.thephantasyrpg.dto.PlayerCreationDTO;
import com.carbon.thephantasyrpg.enums.PlayerCreationViewI18N;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
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
                .withConverter(new DoubleToIntegerConverter(getMessage(PlayerCreationViewI18N.NUMBER_FIELD_ERROR)))
                .bind(getter, setter);
    }
}
