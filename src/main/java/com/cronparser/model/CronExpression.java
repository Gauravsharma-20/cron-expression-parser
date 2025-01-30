package com.cronparser.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CronExpression {
    private final String minute;
    private final String hour;
    private final String dayOfMonth;
    private final String month;
    private final String dayOfWeek;
    private final String command;

    public CronExpression(String minute, String hour, String dayOfMonth, String month, String dayOfWeek, String command) {
        this.minute = minute;
        this.hour = hour;
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.dayOfWeek = dayOfWeek;
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("%-14s %s\n%-14s %s\n%-14s %s\n%-14s %s\n%-14s %s\n%-14s %s",
                "minute", minute,
                "hour", hour,
                "day of month", dayOfMonth,
                "month", month,
                "day of week", dayOfWeek,
                "command", command);
    }
}