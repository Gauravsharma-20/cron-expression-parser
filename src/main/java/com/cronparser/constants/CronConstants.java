package com.cronparser.constants;

import java.util.List;

public class CronConstants {
    public static final String SPACE = " ";

    public static final String WHITESPACE_REGEX = "\\s+";

    public static final List<String> FIELD_NAMES = List.of(FieldNames.MINUTE, FieldNames.HOUR,
            FieldNames.DAY_OF_MONTH, FieldNames.MONTH, FieldNames.DAY_OF_WEEK, FieldNames.COMMAND);

    public static class FieldValues {
        public static final String STAR = "*";
        public static final String COMMA = ",";
        public static final String DASH = "-";
        public static final String SLASH = "/";
    }

    public static class ErrorMessages {
        public static final String INVALID_VALUE_ERROR = "Invalid value: ";
        public static final String INVALID_NUMBER_FORMAT_ERROR = "Invalid number format: ";
        public static final String INVALID_RANGE_ERROR = "Invalid range: ";
        public static final String INVALID_RANGE_FORMAT_ERROR = "Invalid range format: ";
        public static final String INVALID_STEP_ERROR = "Invalid step value: ";
        public static final String INVALID_STEP_FORMAT_ERROR = "Invalid step format: ";
        public static final String INVALID_CRON_EXPRESSION_ERROR = "Invalid cron expression: ";
        public static final String INVALID_FIELD_NAME_ERROR = "Invalid field name: ";

    }

    public static class FieldNames {
        public static final String MINUTE = "minute";
        public static final String HOUR = "hour";
        public static final String DAY_OF_MONTH = "day of month";
        public static final String MONTH = "month";
        public static final String DAY_OF_WEEK = "day of week";
        public static final String COMMAND = "command";
    }
}
