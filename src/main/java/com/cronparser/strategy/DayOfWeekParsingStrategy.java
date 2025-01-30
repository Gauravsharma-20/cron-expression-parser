package com.cronparser.strategy;

import com.cronparser.exception.InvalidCronExpressionException;
import com.cronparser.utils.ExpansionUtils;
import com.cronparser.utils.ValidationUtils;

public class DayOfWeekParsingStrategy implements FieldParsingStrategy {
    private static final int MIN = 0;
    private static final int MAX = 6;

    @Override
    public String parse(String field) throws InvalidCronExpressionException {
        ValidationUtils.validate(field, MIN, MAX);
        return ExpansionUtils.expand(field, MIN, MAX);
    }
}
