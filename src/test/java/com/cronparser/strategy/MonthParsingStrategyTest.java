package com.cronparser.strategy;

import com.cronparser.exception.InvalidCronExpressionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MonthParsingStrategyTest {

    private final MonthParsingStrategy strategy = new MonthParsingStrategy();

    @Test
    void testValidMonth() throws InvalidCronExpressionException {
        assertEquals("1 2 3 4 5 6", strategy.parse("1-6"));
    }

    @Test
    void testInvalidMonthTooLow() {
        assertThrows(InvalidCronExpressionException.class, () -> strategy.parse("0"));
    }

    @Test
    void testInvalidMonthTooHigh() {
        assertThrows(InvalidCronExpressionException.class, () -> strategy.parse("13"));
    }

    @Test
    void testMonthWithWildcard() throws InvalidCronExpressionException {
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12", strategy.parse("*"));
    }
}
