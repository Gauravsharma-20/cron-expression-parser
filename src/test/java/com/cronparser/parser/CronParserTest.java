package com.cronparser.parser;

import com.cronparser.exception.InvalidCronExpressionException;
import com.cronparser.model.CronExpression;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.cronparser.constants.CronConstants.ErrorMessages.*;
import static org.junit.jupiter.api.Assertions.*;

public class CronParserTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private CronParser parser;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
        parser = new CronParser();
    }

    @Test
    public void testValidCronExpression() throws InvalidCronExpressionException {
        String input = "*/15 0 1,15 * 1-5 /usr/bin/find";
        CronExpression result = parser.parse(input);
        assertEquals("0 15 30 45", result.getMinute());
        assertEquals("0", result.getHour());
        assertEquals("1 15", result.getDayOfMonth());
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12", result.getMonth());
        assertEquals("1 2 3 4 5", result.getDayOfWeek());
        assertEquals("/usr/bin/find", result.getCommand());
    }

    @Test
    public void testInvalidCronExpressionLength() {
        String input = "*/15 0 1,15 * 1-5";
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> parser.parse(input));
        assertTrue(exception.getMessage().contains(INVALID_CRON_EXPRESSION_ERROR));
    }

    @Test
    public void testEmptyCronExpression() {
        String input = "";
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> parser.parse(input));
        assertTrue(exception.getMessage().contains(INVALID_CRON_EXPRESSION_ERROR));
    }

    @Test
    public void testInvalidCharacters() {
        String input = "*/15 abc 1,15 * 1-5 /usr/bin/find";
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> parser.parse(input));
        assertTrue(exception.getMessage().contains(INVALID_NUMBER_FORMAT_ERROR));
    }

    @Test
    public void testInvalidMinuteValue() {
        String input = "60 0 1 * * /usr/bin/find";
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> parser.parse(input));
        assertTrue(exception.getMessage().contains(INVALID_VALUE_ERROR));
    }

    @Test
    public void testInvalidHourValue() {
        String input = "0 24 1 * * /usr/bin/find";
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> parser.parse(input));
        assertTrue(exception.getMessage().contains(INVALID_VALUE_ERROR));
    }

    @Test
    public void testInvalidDayOfMonthValue() {
        String input = "0 0 32 * * /usr/bin/find";
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> parser.parse(input));
        assertTrue(exception.getMessage().contains(INVALID_VALUE_ERROR));
    }

    @Test
    public void testInvalidMonthValue() {
        String input = "0 0 1 13 * /usr/bin/find";
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> parser.parse(input));
        assertTrue(exception.getMessage().contains(INVALID_VALUE_ERROR));
    }

    @Test
    public void testInvalidDayOfWeekValue() {
        String input = "0 0 1 * 8 /usr/bin/find";
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> parser.parse(input));
        assertTrue(exception.getMessage().contains(INVALID_VALUE_ERROR));
    }

    @Test
    public void testInvalidRangeFormat() {
        String input = "0 0 1-32 * * /usr/bin/find";
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> parser.parse(input));
        assertTrue(exception.getMessage().contains(INVALID_RANGE_ERROR));
    }

    @Test
    public void testInvalidStepFormat() {
        String input = "*/60 0 1 * * /usr/bin/find";
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> parser.parse(input));
        assertTrue(exception.getMessage().contains(INVALID_STEP_ERROR));
    }

    @Test
    public void testInvalidStepValue() {
        String input = "*/-5 0 1 * * /usr/bin/find";
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> parser.parse(input));
        assertTrue(exception.getMessage().contains(INVALID_STEP_ERROR));
    }

    @Test
    public void testValidCronWithAllFields() throws InvalidCronExpressionException {
        String input = "0 12 1 1 1 /usr/bin/find";
        CronExpression result = parser.parse(input);
        assertEquals("0", result.getMinute());
        assertEquals("12", result.getHour());
        assertEquals("1", result.getDayOfMonth());
        assertEquals("1", result.getMonth());
        assertEquals("1", result.getDayOfWeek());
        assertEquals("/usr/bin/find", result.getCommand());
    }
}