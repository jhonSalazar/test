package com.inclusion.cloud.util;

import java.util.List;

public class ParametersInput {

    public static int PARAMETER_X = 0;
    public static int PARAMETER_Y = 1;
    public static int PARAMETER_N = 2;
    public static int PARAMETER_RESULT = 3;

    public static List<List<Long>> getTests() {

        Long x = 7l;
        Long y = 5L;
        Long n = 12345L;
        Long r = 12339L;
        List<Long> testOne = List.of(x, y, n, r);

        List<List<Long>> tests = List.of(testOne,
                List.of(5L, 0L, 4L, 0L),
                List.of(10L, 5L, 15L, 15L),
                List.of(17L, 8L, 54321L, 54306L),
                List.of(10L, 5L, 187L, 185L),
                List.of(2L, 0L, 999999999L, 999999998L)
        );
        return tests;
    }
}
