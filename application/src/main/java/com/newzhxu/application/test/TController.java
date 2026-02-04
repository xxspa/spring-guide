package com.newzhxu.application.test;

import com.newzhxu.api.DoSomething;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TController {
    private final ObjectProvider<DoSomething> doSomething;
    private final RedissonClient redissonClient;

    @GetMapping("/do")
    public String doIt(String input) {
        String address = redissonClient.getConfig().useSingleServer().getAddress();
        log.info("address:{}", address);
        long test = redissonClient.getAtomicLong("test").incrementAndGet();
        log.info("test:{}", test);
        String s = doSomething.stream()
                .map(e -> e.doIt(input))
                .reduce((a, b) -> a + "~" + b)
                .orElse("");
        return s;
    }
}
