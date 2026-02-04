package com.newzhxu.application.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.redisson.api.RedissonClient;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ATest {
    @InjectMocks
    private RedisTest redisTest;
    @Mock
    private RedissonClient redissonClient;

    @Test
    void testA() {
        when(redissonClient.getLock(anyString())).thenReturn(Mockito.mock(org.redisson.api.RLock.class));

        when(redissonClient.getMap(anyString())).thenReturn(Mockito.mock());
        when(redissonClient.getBloomFilter(anyString())).thenReturn(Mockito.mock());
        System.out.println("Test A is running");
        redisTest.test();
    }
}
