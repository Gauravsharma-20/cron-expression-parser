package com.cronparser;

import com.cronparser.exception.InvalidCronExpressionException;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class MainTest {

    @Test
    void testMainValidCronExpression() throws InvalidCronExpressionException {
        String cronInput = "0 0 * * * command";
        System.setIn(new ByteArrayInputStream(cronInput.getBytes()));

        final PrintStream originalOut = System.out;
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.main(new String[]{});

        String output = outputStream.toString().trim();
        assert output.contains("minute         0");
        assert output.contains("hour           0");
        assert output.contains("day of month   1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31");
        assert output.contains("month          1 2 3 4 5 6 7 8 9 10 11 12");
        assert output.contains("day of week    0 1 2 3 4 5 6");
        assert output.contains("command        command");

        System.setOut(originalOut);
    }

    @Test
    void testMainInvalidCronExpression() {
        String cronInput = "invalid cron";
        System.setIn(new ByteArrayInputStream(cronInput.getBytes()));

        final PrintStream originalOut = System.out;
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.main(new String[]{});

        String output = outputStream.toString().trim();
        assert output.contains("[ERROR]: Invalid cron expression");

        System.setOut(originalOut);
    }
}
