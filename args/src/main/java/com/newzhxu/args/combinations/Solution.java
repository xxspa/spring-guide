package com.newzhxu.args.combinations;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.cn/problems/combinations/">77. 组合</a>
 * <p>回溯</p>
 */
class Solution {

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        dfs(1, n, k, temp, ans);
        return ans;
    }

    private void dfs(int cur, int n, int k, List<Integer> temp, List<List<Integer>> ans) {
        // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
        if (temp.size() + (n - cur + 1) < k) {
            return;
        }
        // 记录合法的答案
        if (temp.size() == k) {
            ans.add(new ArrayList<>(temp));
            return;
        }
        // 考虑选择当前位置
        temp.add(cur);
        dfs(cur + 1, n, k, temp, ans);
        temp.removeLast();
        // 考虑不选择当前位置
        dfs(cur + 1, n, k, temp, ans);
    }

}