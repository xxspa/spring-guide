package com.newzhxu.args.trionicarrayii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("args")
    void maxSumTrionic(int[] nums, long expected) {
        Solution solution = new Solution();
        long actual = solution.maxSumTrionic(nums);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(new int[]{0, -2, -1, -3, 0, 2, -1}, -4),
                Arguments.of(new int[]{1, 4, 2, 7}, 14)
        );
    }
}