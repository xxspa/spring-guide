package com.newzhxu.args.minimumremovalstobalancearray;

import java.util.Arrays;

/**
 * <a href="https://leetcode.cn/problems/minimum-removals-to-balance-array">3634. 使数组平衡的最少移除数目</a>
 */
class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);

        int ans = n;
        int right = 0;

        for (int left = 0; left < n; left++) {
            while (right < n && nums[right] <= (long) nums[left] * k) {
                right++;
            }
            ans = Math.min(ans, n - (right - left));
        }

        return ans;

    }
}