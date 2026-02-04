package com.newzhxu.agent;

import net.bytebuddy.asm.Advice;

import java.math.BigInteger;

public class BaseAgent {
    @Advice.OnMethodExit
    public static void exit(
            @Advice.Argument(0) BigInteger arg,
            @Advice.Argument(1) BigInteger arg1,
            @Advice.Return(readOnly = false) BigInteger result
    ) {
        System.out.println("arg1: " + arg1 + ", arg: " + arg);
//        if (arg.intValue() == 234567) {
//            result = BigInteger.valueOf(999999);
//            System.out.println("Modified Result to: " + result);
//        }
        if (arg.intValue() == 33 && arg1.intValue() == 11) {
            result = BigInteger.valueOf(888888);
            System.out.println("Modified Result to: " + result);
        }


    }
}
