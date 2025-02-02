package com.cronparser.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExpansionUtilsTest {

    @Test
    public void testExpandFullRange() {
        String result = ExpansionUtils.expand("*", 1, 5);
        assertEquals("1 2 3 4 5", result);
    }

    @Test
    public void testExpandRange() {
        String result = ExpansionUtils.expand("1-5", 1, 5);
        assertEquals("1 2 3 4 5", result);
    }

    @Test
    public void testExpandList() {
        String result = ExpansionUtils.expand("1,3,5", 1, 5);
        assertEquals("1 3 5", result);
    }

    @Test
    public void testExpandStep() {
        String result = ExpansionUtils.expand("*/2", 1, 5);
        assertEquals("1 3 5", result);
    }

    @Test
    public void testExpandStepWithRange() {
        String result = ExpansionUtils.expand("2/2", 1, 5);
        assertEquals("2 4", result);
    }

    @Test
    public void testExpandStepWithRangeOutOfBounds() {
        String result = ExpansionUtils.expand("6/2", 1, 5);
        assertEquals("", result);
    }

    @Test
    public void testExpandEmptyField() {
        String result = ExpansionUtils.expand("", 1, 5);
        assertEquals("", result);
    }

    @Test
    public void testExpandStarWithLargeRange() {
        String result = ExpansionUtils.expand("*", 1, 100);
        assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93 94 95 96 97 98 99 100", result);
    }

    @Test
    public void testExpandStepWithStepValueGreaterThanMax() {
        String result = ExpansionUtils.expand("*/200", 1, 5);
        assertEquals("1", result);
    }
}
