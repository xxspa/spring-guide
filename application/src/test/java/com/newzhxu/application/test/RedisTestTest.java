package com.newzhxu.application.test;

import org.junit.jupiter.api.Test;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Import(RedissonAutoConfiguration.class) // 导入你项目中定义 RedissonClient 的配置类
@ActiveProfiles
class RedisTestTest {
    @Autowired
    private RedisTest redisTest;

    @Test
    void test1() {
        redisTest.test();
    }
}