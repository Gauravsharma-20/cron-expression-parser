package com.cronparser.utils;

import com.cronparser.constants.CronConstants.FieldValues;
import com.cronparser.constants.CronConstants.ErrorMessages;
import com.cronparser.exception.InvalidCronExpressionException;

public class ValidationUtils {
    public static void validate(final String field, final int min, final int max) throws InvalidCronExpressionException {
        if (field.equals(FieldValues.STAR)) {
            return;
        }  else if (field.contains(FieldValues.SLASH)) {
            validateStep(field, min, max);
        } else if (field.contains(FieldValues.COMMA)) {
            validateList(field, min, max);
        } else if (field.contains(FieldValues.DASH)) {
            validateRange(field, min, max);
        } else {
            validateSingleValue(field, min, max);
        }
    }

    private static void validateSingleValue(final String field, final int min, final int max) throws InvalidCronExpressionException {
        try {
            final int value = Integer.parseInt(field);
            if (value < min || value > max) {
                throw new InvalidCronExpressionException(ErrorMessages.INVALID_VALUE_ERROR + field);
            }
        } catch (final NumberFormatException e) {
            throw new InvalidCronExpressionException(ErrorMessages.INVALID_NUMBER_FORMAT_ERROR + field);
        }
    }

    private static void validateRange(final String range, final int min, final int max) throws InvalidCronExpressionException {
        final String[] parts = range.split(FieldValues.DASH);
        if (parts.length != 2) {
            throw new InvalidCronExpressionException(ErrorMessages.INVALID_RANGE_FORMAT_ERROR + range);
        }

        try {
            final int start = Integer.parseInt(parts[0]);
            final int end = Integer.parseInt(parts[1]);

            if (start < min || end > max || start > end) {
                throw new InvalidCronExpressionException(ErrorMessages.INVALID_RANGE_ERROR + range);
            }
        } catch (NumberFormatException e) {
            throw new InvalidCronExpressionException(ErrorMessages.INVALID_NUMBER_FORMAT_ERROR + range);
        }
    }

    private static void validateList(String list, int min, int max) throws InvalidCronExpressionException {
        for (String part : list.split(FieldValues.COMMA)) {
            validateSingleValue(part, min, max);
        }
    }

    private static void validateStep(final String stepExpression, final int min, final int max) throws InvalidCronExpressionException {
        final String[] parts = stepExpression.split(FieldValues.SLASH);

        if (parts.length != 2) {
            throw new InvalidCronExpressionException(ErrorMessages.INVALID_STEP_FORMAT_ERROR + stepExpression);
        }

        try {
            validate(parts[0], min, max);
            int step = Integer.parseInt(parts[1]);

            if (step <= 0 || step > max) {
                throw new InvalidCronExpressionException(ErrorMessages.INVALID_STEP_ERROR + step);
            }
        } catch (final NumberFormatException exception) {
            throw new InvalidCronExpressionException(ErrorMessages.INVALID_STEP_FORMAT_ERROR + stepExpression);
        }
    }
}
