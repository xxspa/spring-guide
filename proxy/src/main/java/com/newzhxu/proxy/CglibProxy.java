package com.newzhxu.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class CglibProxy {


    public static void main(String[] args) {
        UserService userService = new UserImpl();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {

            System.out.println("Before method: " + method.getName());
            Object result = method.invoke(userService, objects);
            System.out.println("After method: " + method.getName());
            return result;
        });
        UserService userService1 = (UserService) enhancer.create();
        String result = userService1.getUser(2);
        System.out.println(result); // Should print: User#2
    }
}
