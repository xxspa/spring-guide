package com.newzhxu.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Agent {
    public static void premain(String arguments, Instrumentation instrumentation) {
        agent(arguments, instrumentation);
    }

    public static void agentmain(String agentOps, Instrumentation inst) {
//        agent(agentOps, inst);
        throw new UnsupportedOperationException("暂不支持热加载");

    }

    static void agent(String agentOps, Instrumentation inst) {
//        System.out.println("Agent started with arguments: " + agentOps);
//        // 1. 将 BaseAgent 类注入到 Bootstrap ClassLoader
//        // 这样 BigInteger.add() 才能在运行时找到这个 Advice 类
//        ClassInjector.UsingUnsafe.ofBootLoader()
//                .inject(Collections.singletonMap(
//                        new TypeDescription.ForLoadedType(BaseAgent.class),
//                        ClassFileLocator.ForClassLoader.read(BaseAgent.class)
//                ));
//        new AgentBuilder.Default()
////                .with(AgentBuilder.TypeStrategy.Default.REDEFINE)
//                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
////                .with(AgentBuilder.Listener.StreamWriting.toSystemOut()) 打印日志
//                // 关键点 2: 显式指定忽略规则，不要忽略 BigInteger
//                .ignore(ElementMatchers.none())
//                // 匹配共同的包名
//                .type(ElementMatchers.nameStartsWith("com.newzhxu"))
//                .transform((builder, typeDescription, classLoader, module, protectionDomain) -> {
//                    System.out.println("Transforming: " + typeDescription.getTypeName());
//
//                    // 联合增强：既用 MethodDelegation，又用 Advice
//                    return builder
//
//
//                            // 第二层增强：使用 Advice (注意：visit 通常放在最后)
//                            .visit(Advice.to(TimerAdvice.class)
//                                    .on(ElementMatchers.named("sayHello")));
//                })
//                .type(ElementMatchers.is(BigInteger.class))
//                .transform((builder, typeDescription, classLoader, module, protectionDomain) -> {
//                    System.out.println("Transforming1111 BigInteger: " + typeDescription.getTypeName());
//                    return builder.visit(Advice.to(BaseAgent.class).on(ElementMatchers.named("oddModPow")
//                            .and(ElementMatchers.takesArguments(2))
//                            .and(ElementMatchers.takesArgument(0, BigInteger.class))
//                            .and(ElementMatchers.takesArgument(1, BigInteger.class))));
//                })
//
//                .installOn(inst);
        System.out.println("agentOps: " + agentOps);
        // 1. 准备你的配置数组
        List<TransformationConfig> configs = new ArrayList<>();


        configs.add(new TransformationConfig(
                ElementMatchers.nameStartsWith("com.newzhxu"),
                ElementMatchers.named("sayHello"),
                TimerAdvice.class
        ));
        configs.add(new TransformationConfig(
                ElementMatchers.is(BigInteger.class),
                ElementMatchers.named("oddModPow")
                        .and(ElementMatchers.takesArguments(2))
                        .and(ElementMatchers.takesArgument(0, BigInteger.class))
                        .and(ElementMatchers.takesArgument(1, BigInteger.class)),
                BaseAgent.class
        ));


        // 2. 初始化基础 Builder
        AgentBuilder agentBuilder = new AgentBuilder.Default()
                .with(AgentBuilder.RedefinitionStrategy.RETRANSFORMATION)
                // 忽略所有 Agent 自身的类，只处理业务类和指定的系统类
                .ignore(ElementMatchers.nameStartsWith("com.newzhxu.agent"))
//                .or(ElementMatchers.nameStartsWith("net.bytebuddy"))
                ;
        ;

        // 3. 循环注入配置
        for (TransformationConfig config : configs) {
            agentBuilder = agentBuilder
                    .type(config.getTypeMatcher())
                    .transform((builder, typeDescription, classLoader, module, protectionDomain) -> {
                        System.out.println("Dynamic Transforming: " + typeDescription.getTypeName());
                        return builder.visit(Advice.to(config.getAdviceClass()).on(config.getMethodMatcher()));
                    });
        }

        // 4. 最后安装到 inst
        agentBuilder.installOn(inst);
    }
}
