package com.cronparser.strategy;

import com.cronparser.exception.InvalidCronExpressionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HourParsingStrategyTest {

    private final HourParsingStrategy strategy = new HourParsingStrategy();

    @Test
    void testValidHour() throws InvalidCronExpressionException {
        assertEquals("0 1 2 3 4 5 6", strategy.parse("0-6"));
    }

    @Test
    void testInvalidHourTooLow() {
        assertThrows(InvalidCronExpressionException.class, () -> strategy.parse("-1"));
    }

    @Test
    void testInvalidHourTooHigh() {
        assertThrows(InvalidCronExpressionException.class, () -> strategy.parse("24"));
    }

    @Test
    void testHourWithWildcard() throws InvalidCronExpressionException {
        assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23",
                strategy.parse("*"));
    }
}
