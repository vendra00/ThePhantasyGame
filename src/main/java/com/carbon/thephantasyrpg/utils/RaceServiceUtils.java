package com.carbon.thephantasyrpg.utils;

import com.carbon.thephantasyrpg.enums.RaceServiceI18N;
import com.carbon.thephantasyrpg.enums.Races;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Utility class for Race service
 */
@Slf4j
@Component
public class RaceServiceUtils {

    private final MessageUtils messageUtils;

    public RaceServiceUtils(MessageUtils messageUtils) {
        this.messageUtils = messageUtils;
    }

    public String getMessage(RaceServiceI18N messageEnum) {
        return messageUtils.getMessage(messageEnum.getMessageKey());
    }

    /**
     * Convert an object to a double
     * @param value the object to convert
     * @return the double value
     */
    public Double convertToDouble(Object value) {
        if (value instanceof Integer) {
            return ((Integer) value).doubleValue();
        } else if (value instanceof Double) {
            return (Double) value;
        }
        return null; // or some default value or throw
    }

    /**
     * Convert a string to a enum
     * @param raceName the string to convert
     * @return the enum value
     */
    public Races convertStringToRaceEnum(String raceName) {
        try {
            return Races.valueOf(raceName.toUpperCase().replace(" ", "_"));
        } catch (IllegalArgumentException e) {
            log.warn("Unrecognized race name: '{}'", raceName, e);
            return null;
        }
    }
}
