package com.cronparser.utils;

import com.cronparser.constants.CronConstants.FieldValues;

import static com.cronparser.constants.CronConstants.SPACE;

public class ExpansionUtils {
    public static String expand(String field, int min, int max) {
        if (field.equals(FieldValues.STAR)) {
            return expandFullRange(min, max);
        }  else if (field.contains(FieldValues.SLASH)) {
            return expandStep(field, min, max);
        } else if (field.contains(FieldValues.COMMA)) {
            return expandList(field);
        } else if (field.contains(FieldValues.DASH)) {
            return expandRange(field);
        } else {
            return field;
        }
    }

    private static String expandFullRange(int min, int max) {
        StringBuilder result = new StringBuilder();
        for (int i = min; i <= max; i++) {
            result.append(i).append(SPACE);
        }
        return result.toString().trim();
    }

    private static String expandRange(String range) {
        String[] parts = range.split(FieldValues.DASH);
        int start = Integer.parseInt(parts[0]);
        int end = Integer.parseInt(parts[1]);

        StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i++) {
            result.append(i).append(SPACE);
        }
        return result.toString().trim();
    }

    private static String expandList(final String list) {
        return list.replace(FieldValues.COMMA, SPACE);
    }

    private static String expandStep(final String stepExpression, final int min, final int max) {
        if (stepExpression.startsWith(FieldValues.STAR)) {
            final int step = Integer.parseInt(stepExpression.substring(2));
            return expandRangeWithStep(min, max, step);
        }

        final String[] parts = stepExpression.split(FieldValues.SLASH);
        final int start = Integer.parseInt(parts[0]);
        final int step = Integer.parseInt(parts[1]);

        return expandRangeWithStep(start, max, step);
    }

    private static String expandRangeWithStep(final int start, final int end, final int step) {
        final StringBuilder result = new StringBuilder();
        for (int i = start; i <= end; i += step) {
            result.append(i).append(SPACE);
        }
        return result.toString().trim();
    }
}
