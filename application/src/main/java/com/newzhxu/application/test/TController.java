package com.newzhxu.application.test;

import com.newzhxu.api.DoSomething;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TController {
    private final ObjectProvider<DoSomething> doSomething;

    @GetMapping("/do")
    public String doIt(String input) {
        String s = doSomething.stream()
                .map(e -> e.doIt(input))
                .reduce((a, b) -> a + "~" + b)
                .orElse("");
        return s;
    }
}
