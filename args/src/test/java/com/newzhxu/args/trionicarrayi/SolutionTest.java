package com.newzhxu.args.trionicarrayi;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("args")
    void isTrionic(int[] nums, boolean expected) {
        Solution solution = new Solution();
        boolean actual = solution.isTrionic(nums);
        assert actual == expected;
    }

    static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(new int[]{1, 3, 4, 5, 2, 6}, true),
                Arguments.of(new int[]{2, 1, 3}, false)
        );
    }
}