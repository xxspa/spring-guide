package com.newzhxu.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        // sentinel 限流的核心逻辑示例，令牌桶算法实现
        // 1. 初始化限流规则
        initFlowRules();

        while (true) {
            // 2. 尝试进入资源（名为 HelloWorld）
            try (Entry entry = SphU.entry("HelloWorld")) {
                /* 你的业务逻辑开始 */
                System.out.println("hello world - 访问成功");
                /* 你的业务逻辑结束 */
            } catch (BlockException ex) {
                /* 资源访问被限流、被降级或被异常所抛出的处理逻辑 */
                System.err.println("blocked! - 访问被拦截");
            }

            // 为了方便观察，睡眠 100ms
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("HelloWorld");       // 资源名，需与上文 SphU.entry 一致
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); // 限流阈值类型：QPS
        rule.setCount(5);                     // 每秒最多允许 5 个请求
        rules.add(rule);
        // 加载规则
        FlowRuleManager.loadRules(rules);
    }
}
