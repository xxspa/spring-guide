package com.newzhxu.args.divideanarrayintosubarrayswithminimumcosti;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("args")
    void minimumCost(int[] nums, int expected) {
        Solution solution = new Solution();
        int actual = solution.minimumCost(nums);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 12}, 6),
                Arguments.of(new int[]{5, 4, 3}, 12),
                Arguments.of(new int[]{10, 3, 1, 1}, 12)
        );
    }
}