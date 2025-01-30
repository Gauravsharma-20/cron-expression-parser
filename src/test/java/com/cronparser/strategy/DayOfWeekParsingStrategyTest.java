package com.cronparser.strategy;

import com.cronparser.exception.InvalidCronExpressionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DayOfWeekParsingStrategyTest {

    private final DayOfWeekParsingStrategy strategy = new DayOfWeekParsingStrategy();

    @Test
    void testValidDayOfWeek() throws InvalidCronExpressionException {
        assertEquals("0 1 2 3 4 5 6", strategy.parse("0-6"));
    }

    @Test
    void testInvalidDayOfWeekTooLow() {
        assertThrows(InvalidCronExpressionException.class, () -> strategy.parse("-1"));
    }

    @Test
    void testInvalidDayOfWeekTooHigh() {
        assertThrows(InvalidCronExpressionException.class, () -> strategy.parse("7"));
    }

    @Test
    void testDayOfWeekWithWildcard() throws InvalidCronExpressionException {
        assertEquals("0 1 2 3 4 5 6", strategy.parse("*"));
    }
}
