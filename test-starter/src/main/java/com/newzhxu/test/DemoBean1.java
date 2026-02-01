package com.newzhxu.test;

import com.newzhxu.api.DoSomething;

public class DemoBean1 implements DoSomething {
    @Override
    public String doIt(String input) {
        return input + "2";
    }
}
