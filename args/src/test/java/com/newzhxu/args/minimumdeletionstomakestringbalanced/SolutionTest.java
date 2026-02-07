package com.newzhxu.args.minimumdeletionstomakestringbalanced;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SolutionTest {

    @ParameterizedTest
    @MethodSource("args")
    void minimumDeletions(String s, int expected) {
        Solution solution = new Solution();
        int actual = solution.minimumDeletions(s);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of("aababbab", 2),
                Arguments.of("bbaaaaabb", 2)
        );
    }
}