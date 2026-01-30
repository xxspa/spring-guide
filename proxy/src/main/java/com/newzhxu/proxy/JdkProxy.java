package com.newzhxu.proxy;

import java.lang.reflect.Proxy;

public class JdkProxy {

    UserService getProxy() {
        Object testMethod = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(),
                new Class[]{UserService.class},
                (proxy, method, args) -> {
                    if (method.getName().equals("testMethod")) {
                        return "Proxyed: " + args[0];
                    }
                    return null;
                });
        return (UserService) testMethod;

    }

    public static void main(String[] args) {
        UserImpl target = new UserImpl();
        UserService userService = (UserService) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args1) -> {
                    System.out.println("Before method: " + method.getName());
                    return method.invoke(target, args1);
                });
        String result = userService.getUser(1);
        System.out.println(result); // Should print: User#1

    }
}
