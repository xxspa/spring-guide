package com.newzhxu.proxy.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

public class Demo2 {
    public static void main(String[] args) {
        try (DynamicType.Unloaded<Object> objectUnloaded = new ByteBuddy()

                .subclass(Object.class)
                .name("com.Demo2")
                .make()) {
            Class<? extends DynamicType.Unloaded> aClass = objectUnloaded.getClass();
            System.out.println(aClass.getName());
        }
    }
}
