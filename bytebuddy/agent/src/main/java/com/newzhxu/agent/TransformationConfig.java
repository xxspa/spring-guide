package com.newzhxu.agent;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.matcher.ElementMatcher;

public class TransformationConfig {
    private final ElementMatcher<TypeDescription> typeMatcher;
    private final ElementMatcher<MethodDescription> methodMatcher;
    private final Class<?> adviceClass;

    public TransformationConfig(ElementMatcher<TypeDescription> typeMatcher,
                                ElementMatcher<MethodDescription> methodMatcher,
                                Class<?> adviceClass) {
        this.typeMatcher = typeMatcher;
        this.methodMatcher = methodMatcher;
        this.adviceClass = adviceClass;
    }

    // Getters...
    public ElementMatcher<TypeDescription> getTypeMatcher() {
        return typeMatcher;
    }

    public ElementMatcher<MethodDescription> getMethodMatcher() {
        return methodMatcher;
    }

    public Class<?> getAdviceClass() {
        return adviceClass;
    }
}