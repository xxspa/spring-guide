package com.newzhxu.args.transformedarray;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("args")
    void constructTransformedArray(int[] nums, int[] expected) {
        Solution solution = new Solution();
        int[] result = solution.constructTransformedArray(nums);
        Assertions.assertArrayEquals(expected, result);
    }

    static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(new int[]{3, -2, 1, 1}, new int[]{1, 1, 1, 3}),
                Arguments.of(new int[]{-1, 4, -1}, new int[]{-1, -1, 4})
        );
    }
}