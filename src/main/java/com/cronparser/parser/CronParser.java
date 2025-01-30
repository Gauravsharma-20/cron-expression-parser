package com.cronparser.parser;

import com.cronparser.exception.InvalidCronExpressionException;
import com.cronparser.model.CronExpression;
import com.cronparser.strategy.FieldParsingStrategy;
import com.cronparser.factory.FieldParsingStrategyFactory;

import static com.cronparser.constants.CronConstants.FIELD_NAMES;
import static com.cronparser.constants.CronConstants.WHITESPACE_REGEX;
import static com.cronparser.constants.CronConstants.ErrorMessages.INVALID_CRON_EXPRESSION_ERROR;

public class CronParser {

    public CronExpression parse(final String cronExpression) throws InvalidCronExpressionException {
        final String[] fields = cronExpression.split(WHITESPACE_REGEX);

        if (fields.length != FIELD_NAMES.size()) {
            throw new InvalidCronExpressionException(INVALID_CRON_EXPRESSION_ERROR + cronExpression);
        }

        final String[] parsedFields = new String[fields.length];

        for (int fieldIndex = 0; fieldIndex < fields.length - 1; fieldIndex++) {
            final String fieldName = FIELD_NAMES.get(fieldIndex);
            final FieldParsingStrategy strategy = FieldParsingStrategyFactory.getStrategy(fieldName);
            parsedFields[fieldIndex] = strategy.parse(fields[fieldIndex]);
        }

        parsedFields[fields.length - 1] = fields[fields.length - 1]; // "command" field is passed directly

        return CronExpression.builder()
                .minute(parsedFields[0])
                .hour(parsedFields[1])
                .dayOfMonth(parsedFields[2])
                .month(parsedFields[3])
                .dayOfWeek(parsedFields[4])
                .command(parsedFields[5])
                .build();
    }
}
