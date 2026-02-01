package com.newzhxu.args.palindromenumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("args")
    void isPalindrome(int x, boolean expected) {
        Solution solution = new Solution();
        boolean actual = solution.isPalindrome(x);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(121, true),
                Arguments.of(-121, false),
                Arguments.of(10, false)
        );
    }
}