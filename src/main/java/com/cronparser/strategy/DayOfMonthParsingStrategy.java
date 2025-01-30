package com.cronparser.strategy;

import com.cronparser.exception.InvalidCronExpressionException;
import com.cronparser.utils.ExpansionUtils;
import com.cronparser.utils.ValidationUtils;

public class DayOfMonthParsingStrategy implements FieldParsingStrategy {
    private static final int MIN = 1;
    private static final int MAX = 31;

    @Override
    public String parse(String field) throws InvalidCronExpressionException {
        ValidationUtils.validate(field, MIN, MAX);
        return ExpansionUtils.expand(field, MIN, MAX);
    }
}
