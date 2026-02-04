package com.newzhxu.application.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RedisTest {
    private final RedissonClient redissonClient;

    public void test() {
        RLock lock = redissonClient.getLock("lock");
        lock.lock();
        redissonClient.getMap("map").put("key1", "value1");
        redissonClient.getBloomFilter("bloom").tryInit(1000L, 0.03);
        redissonClient.getBloomFilter("bloom").add("value1");
        log.info("Added key1 to map and bloom filter");
    }
}
