package com.newzhxu.args.removeelement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

class SolutionTest {
    Solution s = new Solution();

    @ParameterizedTest
    @MethodSource("args")
    void removeElement(int[] nums, int val, int[] expectedNums) {

        int i = s.removeElement(nums, val);
        Assertions.assertEquals(expectedNums.length, i);
        int[] expectedSorted = Arrays.copyOf(expectedNums, expectedNums.length);
        Arrays.sort(expectedSorted);
        int[] actual = Arrays.copyOf(nums, i);
        Arrays.sort(actual);
        Assertions.assertArrayEquals(expectedSorted, actual);
    }

    static Stream<Arguments> args() {
        return Stream.of(Arguments.of(new int[]{3, 2, 2, 3}, 3, new int[]{2, 2}),
                Arguments.of(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2, new int[]{0, 1, 4, 0, 3})

        );
    }
}