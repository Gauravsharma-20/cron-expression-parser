package com.cronparser.utils;

import com.cronparser.exception.InvalidCronExpressionException;
import com.cronparser.constants.CronConstants.ErrorMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationUtilsTest {

    @Test
    public void testValidSingleValue() {
        try {
            ValidationUtils.validate("5", 1, 10);
        } catch (InvalidCronExpressionException e) {
            fail("Valid value should not throw an exception.");
        }
    }

    @Test
    public void testInvalidSingleValue() {
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> {
            ValidationUtils.validate("11", 1, 10);
        });
        assertTrue(exception.getMessage().contains(ErrorMessages.INVALID_VALUE_ERROR));
    }

    @Test
    public void testValidRange() {
        try {
            ValidationUtils.validate("5-8", 1, 10);
        } catch (InvalidCronExpressionException e) {
            fail("Valid range should not throw an exception.");
        }
    }

    @Test
    public void testInvalidRange() {
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> {
            ValidationUtils.validate("8-5", 1, 10);
        });
        assertTrue(exception.getMessage().contains(ErrorMessages.INVALID_RANGE_ERROR));
    }

    @Test
    public void testInvalidRangeOutOfBounds() {
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> {
            ValidationUtils.validate("11-15", 1, 10);
        });
        assertTrue(exception.getMessage().contains(ErrorMessages.INVALID_RANGE_ERROR));
    }

    @Test
    public void testValidList() {
        try {
            ValidationUtils.validate("1,3,5", 1, 10);
        } catch (InvalidCronExpressionException e) {
            fail("Valid list should not throw an exception.");
        }
    }

    @Test
    public void testInvalidList() {
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> {
            ValidationUtils.validate("1,3,12", 1, 10);
        });
        assertTrue(exception.getMessage().contains(ErrorMessages.INVALID_VALUE_ERROR));
    }

    @Test
    public void testValidStep() {
        try {
            ValidationUtils.validate("*/2", 1, 10);
        } catch (InvalidCronExpressionException e) {
            fail("Valid step expression should not throw an exception.");
        }
    }

    @Test
    public void testInvalidStep() {
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> {
            ValidationUtils.validate("*/0", 1, 10);
        });
        assertTrue(exception.getMessage().contains(ErrorMessages.INVALID_STEP_ERROR));
    }

    @Test
    public void testInvalidStepFormat() {
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> {
            ValidationUtils.validate("5/2/3", 1, 10);
        });
        assertTrue(exception.getMessage().contains(ErrorMessages.INVALID_STEP_FORMAT_ERROR));
    }

    @Test
    public void testValidStar() {
        try {
            ValidationUtils.validate("*", 1, 10);
        } catch (InvalidCronExpressionException e) {
            fail("Star expression should not throw an exception.");
        }
    }

    @Test
    public void testInvalidNumberFormat() {
        Exception exception = assertThrows(InvalidCronExpressionException.class, () -> {
            ValidationUtils.validate("abc", 1, 10);
        });
        assertTrue(exception.getMessage().contains(ErrorMessages.INVALID_NUMBER_FORMAT_ERROR));
    }
}
