package com.newzhxu.target;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class Main {
    //    -javaagent:bytebuddy/agent/build/libs/agent.jar
    public static void main(String[] args) throws InterruptedException {
        long pid = ProcessHandle.current().pid();
        System.out.println("Current PID: " + pid);
        while (true) {
            BigInteger bigInteger = BigInteger.valueOf(414);
            BigInteger add = bigInteger.modPow(BigInteger.valueOf(33), BigInteger.valueOf(44));
            System.out.println("Result: " + add);
            TimeUnit.SECONDS.sleep(3);
        }
    }
}
