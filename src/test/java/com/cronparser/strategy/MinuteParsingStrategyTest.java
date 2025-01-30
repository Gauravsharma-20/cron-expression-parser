package com.cronparser.strategy;

import com.cronparser.exception.InvalidCronExpressionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MinuteParsingStrategyTest {

    private final MinuteParsingStrategy strategy = new MinuteParsingStrategy();

    @Test
    void testValidMinute() throws InvalidCronExpressionException {
        assertEquals("0 1 2 3 4 5 6", strategy.parse("0-6"));
    }

    @Test
    void testInvalidMinuteTooLow() {
        assertThrows(InvalidCronExpressionException.class, () -> strategy.parse("-1"));
    }

    @Test
    void testInvalidMinuteTooHigh() {
        assertThrows(InvalidCronExpressionException.class, () -> strategy.parse("60"));
    }

    @Test
    void testMinuteWithWildcard() throws InvalidCronExpressionException {
        assertEquals("0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24" +
                " 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 " +
                "51 52 53 54 55 56 57 58 59", strategy.parse("*"));
    }
}
