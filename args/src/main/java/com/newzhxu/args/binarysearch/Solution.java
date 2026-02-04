package com.newzhxu.args.binarysearch;

/**
 * <a href="https://leetcode.cn/problems/binary-search">704. 二分查找</a>
 * <br/
 * <a>数组</a>
 */
class Solution {
    public int search(int[] nums, int target) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        return -1;

    }
}