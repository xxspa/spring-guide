package com.newzhxu.args.permutations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SolutionTest {
    Solution solution = new Solution();

    @ParameterizedTest
    @MethodSource("args")
    void permute(int[] nums, List<List<Integer>> expected) {
        Comparator<List<Integer>> comparator = Comparator.comparing(List::toString);
        List<List<Integer>> permute = solution.permute(nums);
        List<List<Integer>> normActual = permute.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        List<List<Integer>> normExpected = expected.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        Assertions.assertEquals(normExpected, normActual);
    }

    static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3}, List.of(
                        List.of(1, 3, 2),
                        List.of(2, 3, 1),
                        List.of(3, 1, 2),
                        List.of(2, 1, 3),
                        List.of(3, 2, 1),
                        List.of(1, 2, 3)
                )),
                Arguments.of(new int[]{0, 1}, List.of(
                        List.of(0, 1),
                        List.of(1, 0))
                ),
                Arguments.of(new int[]{1}, List.of(List.of(1)))
        );
    }
}