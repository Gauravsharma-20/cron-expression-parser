package com.cronparser.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidCronExpressionExceptionTest {

    @Test
    void testExceptionMessage() {
        String errorMessage = "Invalid cron expression format";
        InvalidCronExpressionException exception = new InvalidCronExpressionException(errorMessage);

        assertEquals("[ERROR]: " + errorMessage, exception.getMessage(), "Exception message should match the expected format");
    }
}
