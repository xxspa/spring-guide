package com.newzhxu.args.divideanarrayintosubarrayswithminimumcosti;

/**
 * <a href="https://leetcode.cn/problems/divide-an-array-into-subarrays-with-minimum-cost-i/">3010. 将数组分成最小总代价的子数组 I</a>
 */
class Solution {
    public int minimumCost(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int i = 1; i < nums.length; i++) {
            int x = nums[i];
            if (x < first) {
                second = first;
                first = x;
            } else if (x < second) {
                second = x;
            }
        }

        return nums[0] + first + second;
    }
}