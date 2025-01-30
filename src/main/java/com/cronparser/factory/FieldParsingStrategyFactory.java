package com.cronparser.factory;

import com.cronparser.constants.CronConstants.FieldNames;
import com.cronparser.strategy.MinuteParsingStrategy;
import com.cronparser.strategy.HourParsingStrategy;
import com.cronparser.strategy.DayOfMonthParsingStrategy;
import com.cronparser.strategy.MonthParsingStrategy;
import com.cronparser.strategy.DayOfWeekParsingStrategy;
import com.cronparser.strategy.FieldParsingStrategy;

import static com.cronparser.constants.CronConstants.ErrorMessages.INVALID_FIELD_NAME_ERROR;

public class FieldParsingStrategyFactory {
    public static FieldParsingStrategy getStrategy(String fieldName) {

        return switch (fieldName) {
            case FieldNames.MINUTE -> new MinuteParsingStrategy();
            case FieldNames.HOUR -> new HourParsingStrategy();
            case FieldNames.DAY_OF_MONTH -> new DayOfMonthParsingStrategy();
            case FieldNames.MONTH -> new MonthParsingStrategy();
            case FieldNames.DAY_OF_WEEK -> new DayOfWeekParsingStrategy();
            default -> throw new IllegalArgumentException(INVALID_FIELD_NAME_ERROR + fieldName);
        };
    }
}
