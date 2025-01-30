package com.cronparser.strategy;

import com.cronparser.exception.InvalidCronExpressionException;
import com.cronparser.utils.ExpansionUtils;
import com.cronparser.utils.ValidationUtils;

public class HourParsingStrategy implements FieldParsingStrategy {
    private static final int MIN = 0;
    private static final int MAX = 23;

    @Override
    public String parse(String field) throws InvalidCronExpressionException {
        ValidationUtils.validate(field, MIN, MAX);
        return ExpansionUtils.expand(field, MIN, MAX);
    }
}
