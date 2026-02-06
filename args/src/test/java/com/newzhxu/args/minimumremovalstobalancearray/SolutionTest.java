package com.newzhxu.args.minimumremovalstobalancearray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("args")
    void minRemoval(int[] nums, int k, int expected) {
        Solution solution = new Solution();
        int result = solution.minRemoval(nums, k);
        Assertions.assertEquals(expected, result);
    }

    static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(new int[]{2, 1, 5}, 2, 1),
                Arguments.of(new int[]{1, 6, 2, 9}, 3, 2),
                Arguments.of(new int[]{4, 6}, 2, 0)
        );
    }
}