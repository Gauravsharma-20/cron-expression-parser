package com.cronparser.factory;

import com.cronparser.strategy.FieldParsingStrategy;
import com.cronparser.strategy.MinuteParsingStrategy;
import com.cronparser.strategy.HourParsingStrategy;
import com.cronparser.strategy.DayOfMonthParsingStrategy;
import com.cronparser.strategy.MonthParsingStrategy;
import com.cronparser.strategy.DayOfWeekParsingStrategy;
import com.cronparser.constants.CronConstants.FieldNames;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldParsingStrategyFactoryTest {

    @Test
    void testGetStrategyForMinuteField() {
        FieldParsingStrategy strategy = FieldParsingStrategyFactory.getStrategy(FieldNames.MINUTE);
        assertInstanceOf(MinuteParsingStrategy.class, strategy, "Expected MinuteParsingStrategy for MINUTE field");
    }

    @Test
    void testGetStrategyForHourField() {
        FieldParsingStrategy strategy = FieldParsingStrategyFactory.getStrategy(FieldNames.HOUR);
        assertInstanceOf(HourParsingStrategy.class, strategy, "Expected HourParsingStrategy for HOUR field");
    }

    @Test
    void testGetStrategyForDayOfMonthField() {
        FieldParsingStrategy strategy = FieldParsingStrategyFactory.getStrategy(FieldNames.DAY_OF_MONTH);
        assertInstanceOf(DayOfMonthParsingStrategy.class, strategy, "Expected DayOfMonthParsingStrategy for DAY_OF_MONTH field");
    }

    @Test
    void testGetStrategyForMonthField() {
        FieldParsingStrategy strategy = FieldParsingStrategyFactory.getStrategy(FieldNames.MONTH);
        assertInstanceOf(MonthParsingStrategy.class, strategy, "Expected MonthParsingStrategy for MONTH field");
    }

    @Test
    void testGetStrategyForDayOfWeekField() {
        FieldParsingStrategy strategy = FieldParsingStrategyFactory.getStrategy(FieldNames.DAY_OF_WEEK);
        assertInstanceOf(DayOfWeekParsingStrategy.class, strategy, "Expected DayOfWeekParsingStrategy for DAY_OF_WEEK field");
    }

    @Test
    void testGetStrategyForInvalidField() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            FieldParsingStrategyFactory.getStrategy("INVALID_FIELD");
        });
        assertEquals("Invalid field name: INVALID_FIELD", exception.getMessage(), "Expected error message for invalid field");
    }
}
