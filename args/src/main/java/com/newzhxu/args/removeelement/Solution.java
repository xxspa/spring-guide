package com.newzhxu.args.removeelement;

/**
 * <a href="https://leetcode.cn/problems/remove-element/">27. 移除元素</a>
 * <br/
 * <a>数组</a>
 */
class Solution {
    public int removeElement(int[] nums, int val) {
        int n = nums.length;
        int left = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] != val) {
                nums[left] = nums[right];
                left++;
            }
        }
        return left;

    }
}