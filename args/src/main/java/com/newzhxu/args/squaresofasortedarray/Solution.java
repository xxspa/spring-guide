package com.newzhxu.args.squaresofasortedarray;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/squares-of-a-sorted-array">977. 有序数组的平方</a>
 * <br/
 * <a>数组</a>
 */
class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i] * nums[i];
        }
        Arrays.sort(res);
        return res;
    }
}