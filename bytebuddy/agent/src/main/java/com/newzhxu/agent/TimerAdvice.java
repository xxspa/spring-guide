package com.newzhxu.agent;

import net.bytebuddy.asm.Advice;

public class TimerAdvice {

    // 方法进入时：记录当前时间，并返回给 Exit 方法
    @Advice.OnMethodEnter
    public static long enter() {
        return System.currentTimeMillis();
    }

    // 方法退出时：接收 Enter 返回的时间戳，并获取入参进行逻辑判断
    @Advice.OnMethodExit
    public static void exit(
            @Advice.Enter long startTime,
            @Advice.Argument(0) String arg,
            @Advice.Return(readOnly = false) String result,
            
            @Advice.Thrown Throwable throwable

    ) {

        long duration = System.currentTimeMillis() - startTime;

        System.out.println("Method Argument: " + arg);
        System.out.println("Execution Time: " + duration + "ms");

        // 满足条件：耗时超过 100ms 或者是特定入参，修改返回值
        if (duration > 100 || "test".equals(arg)) {
            result = result + " (Performance Monitored)";
        }
    }
}