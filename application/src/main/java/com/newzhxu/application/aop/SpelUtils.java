package com.newzhxu.application.aop;

import lombok.experimental.UtilityClass;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

@UtilityClass
public class SpelUtils {
    // 解析器，线程安全
    private static final ExpressionParser parser = new SpelExpressionParser();
    // 用于获取方法的参数名
    private static final DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    public static String parseKey(String spel, Method method, Object[] args) {
        // 1. 获取方法参数名数组
        String[] params = nameDiscoverer.getParameterNames(method);
        if (params == null || params.length == 0) {
            return spel;
        }

        // 2. 将参数名与参数值绑定到上下文
        EvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < params.length; i++) {
            context.setVariable(params[i], args[i]);
        }

        // 3. 解析表达式并获取结果
        Expression expression = parser.parseExpression(spel);
        Object value = expression.getValue(context);

        return value != null ? value.toString() : "";
    }
}