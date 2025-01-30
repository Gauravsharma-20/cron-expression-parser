package com.cronparser.strategy;

import com.cronparser.exception.InvalidCronExpressionException;

public interface FieldParsingStrategy {
    String parse(String field) throws InvalidCronExpressionException;
}