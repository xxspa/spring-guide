package com.newzhxu.args.reverselinkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class SolutionTest {
    Solution solution = new Solution();

    @ParameterizedTest
    @MethodSource("args")
    void reverseList(ListNode head, ListNode expected) {
        ListNode result = solution.reverseList(head);
        Assertions.assertEquals(expected, result);

    }

    static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))),
                        new ListNode(5, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(1)))))),
                Arguments.of(new ListNode(1, new ListNode(2)), new ListNode(2, new ListNode(1))),
                Arguments.of(null, null)
        );
    }
}