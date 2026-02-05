package com.newzhxu.args.transformedarray;

/**
 * <a href="https://leetcode.cn/problems/transformed-array">3379. 转换数组</a>
 */
class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        for (int i = 0; i < nums.length; i++) {
            int index = ((i + nums[i]) % length + length) % length;
            res[i] = nums[index];
        }

        return res;
    }
}