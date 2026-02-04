package com.newzhxu.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/a")
@RestController
public class TestContrller {
    @GetMapping
    public String a() {
        return "a";
    }
}
