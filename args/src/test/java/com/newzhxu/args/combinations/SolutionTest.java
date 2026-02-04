package com.newzhxu.args.combinations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SolutionTest {
    Solution s = new Solution();

    @ParameterizedTest
    @MethodSource("args")
    void combine(int n, int k, List<List<Integer>> res) {
        List<List<Integer>> combine = s.combine(n, k);
        Comparator<List<Integer>> comparator = Comparator.comparing(List::toString);
        Assertions.assertEquals(
                res.stream().sorted(comparator).collect(Collectors.toList()),
                combine.stream().sorted(comparator).collect(Collectors.toList())
        );
    }

    static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(4, 2, List.of(
                        List.of(2, 4),
                        List.of(3, 4),
                        List.of(2, 3),
                        List.of(1, 2),
                        List.of(1, 3),
                        List.of(1, 4)
                )),
                Arguments.of(1, 1, List.of(List.of(1))));

    }
}