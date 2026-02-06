package com.newzhxu.sentinel;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Demo {
    // 1. 定义一个信号量（倒计时锁），初始值为 1
    private static final CountDownLatch shutdownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        // sentinel 限流的核心逻辑示例，令牌桶算法实现
        // 1. 初始化限流规则
        initFlowRules();
        new Thread(() -> {
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
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "sentinel").start();

        // 2. 注册 Shutdown Hook (监听 Ctrl+C 或 kill -15)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\n[系统通知] 检测到 Ctrl+C 停止信号，准备优雅退出...");

            // 在这里执行清理工作
            // 例如：关闭线程池、保存监控数据、断开数据库连接

            // 3. 释放信号量，让主线程继续往下走
            shutdownLatch.countDown();
        }));
        System.out.println("服务已启动。");
        System.out.println("主线程已进入阻塞状态（非空转），监控中...");
        System.out.println("您可以按下 Ctrl+C 来停止项目。");
        try {
            // 4. 主线程阻塞在这里，等待 latch 变为 0
            // 这不会消耗任何 CPU 资源，因为线程被挂起了
            shutdownLatch.await();
        } catch (InterruptedException e) {
            System.err.println("主线程被意外中断");
        }

        System.out.println("[系统通知] 信号量已释放，程序正常结束。");


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
