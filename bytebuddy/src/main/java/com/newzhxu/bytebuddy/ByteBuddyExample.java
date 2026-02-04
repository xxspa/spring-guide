package com.newzhxu.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyExample {
    public static void main(String[] args) throws Exception {

        // 1. 创建 ByteBuddy 实例并定义转换逻辑
        Class<?> dynamicType = new ByteBuddy()
                .subclass(GreetingService.class) // 指定要拦截的父类
                .method(ElementMatchers.named("sayHello")) // 匹配名为 sayHello 的方法
                .intercept(FixedValue.value("ByteBuddy is Awesome!")) // 强制修改返回值为固定值
                .make()
                .load(GreetingService.class.getClassLoader(), ClassLoadingStrategy.Default.INJECTION)
                .getLoaded();

        // 2. 实例化这个动态生成的子类
        GreetingService instance = (GreetingService) dynamicType.getDeclaredConstructor().newInstance();

        // 3. 测试结果
        System.out.println("修改前的默认行为: " + new GreetingService().sayHello());
        System.out.println("修改后的动态行为: " + instance.sayHello());
    }
}