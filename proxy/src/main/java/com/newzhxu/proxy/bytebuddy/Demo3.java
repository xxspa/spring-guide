package com.newzhxu.proxy.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.NamingStrategy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

public class Demo3 {
    public static void main(String[] args) {
        try (DynamicType.Unloaded<Object> objectUnloaded = new ByteBuddy()
                .with(new NamingStrategy.AbstractBase() {
                    @Override
                    protected String name(TypeDescription superClass) {
                        return "i.love.ByteBuddy." + superClass.getSimpleName();
                    }
                }).subclass(Object.class)
                .make()) {
        }
    }
}
