package com.newzhxu.test;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicInteger;

public class A {
    static AtomicInteger a = new AtomicInteger();

    public A() {
        System.out.println("a = " + a.incrementAndGet());
    }


    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        A a1 = new A();
        A a2 = new A();
        A a3 = new A();
        A a4 = A.class.getConstructor().newInstance();
        
    }
}
