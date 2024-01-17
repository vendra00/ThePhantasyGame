package com.carbon.thephantasyrpg.utils;

import com.vaadin.flow.data.converter.Converter;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;

public class DoubleToIntegerConverter implements Converter<Double, Integer> {
    private final String errorMessage;

    public DoubleToIntegerConverter(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Result<Integer> convertToModel(Double fieldValue, ValueContext context) {
        if (fieldValue == null) {
            return Result.ok(null);
        }
        try {
            return Result.ok(fieldValue.intValue());
        } catch (NumberFormatException e) {
            return Result.error(errorMessage);
        }
    }

    @Override
    public Double convertToPresentation(Integer modelValue, ValueContext context) {
        if (modelValue == null) {
            return null;
        }
        return modelValue.doubleValue();
    }
}

