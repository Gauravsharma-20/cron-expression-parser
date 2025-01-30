package com.cronparser.strategy;

import com.cronparser.exception.InvalidCronExpressionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DayOfMonthParsingStrategyTest {

    private final DayOfMonthParsingStrategy strategy = new DayOfMonthParsingStrategy();

    @Test
    void testValidDayOfMonth() throws InvalidCronExpressionException {
        assertEquals("1 2 3 4 5 6 7", strategy.parse("1-7"));
    }

    @Test
    void testInvalidDayOfMonthTooLow() {
        assertThrows(InvalidCronExpressionException.class, () -> strategy.parse("0"));
    }

    @Test
    void testInvalidDayOfMonthTooHigh() {
        assertThrows(InvalidCronExpressionException.class, () -> strategy.parse("32"));
    }

    @Test
    void testDayOfMonthWithWildcard() throws InvalidCronExpressionException {
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31",
                strategy.parse("*"));
    }
}
