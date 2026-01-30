package com.newzhxu.proxy;

import org.junit.jupiter.api.Test;

class JdkProxyTest {

    @Test
    void getProxy() {
        UserService proxy = new JdkProxy().getProxy();
//        String result = proxy.testMethod("Hello");
//        System.out.println(result); // Should print: Proxyed: Hello
    }
}