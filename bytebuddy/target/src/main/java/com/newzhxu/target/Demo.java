package com.newzhxu.target;

import java.util.concurrent.TimeUnit;

public class Demo {
    public String sayHello(String s) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return "Hello World!";
    }
}
