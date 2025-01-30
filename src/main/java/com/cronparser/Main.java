package com.cronparser;

import com.cronparser.exception.InvalidCronExpressionException;
import com.cronparser.model.CronExpression;
import com.cronparser.parser.CronParser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a cron expression: ");
        final String cronExpression = scanner.nextLine();

        try {
            final CronParser parser = new CronParser();
            final CronExpression parsedCronExpression = parser.parse(cronExpression);
            System.out.println(parsedCronExpression.toString());

        } catch (final InvalidCronExpressionException exception) {
            System.out.println(exception.getMessage());
        }
    }
}