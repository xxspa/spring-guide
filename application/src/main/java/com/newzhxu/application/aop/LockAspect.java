package com.newzhxu.application.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Order(-1)
@RequiredArgsConstructor
public class LockAspect {

    private final RedissonClient redissonClient;

    @Around(value = "@annotation(redisLock)")
    public Object lock(ProceedingJoinPoint joinPoint, RedisLock redisLock) throws Throwable {
        // 获取方法签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        // 解析动态 Key
        String lockKey = SpelUtils.parseKey(redisLock.key(), method, joinPoint.getArgs());
        RLock lock = redissonClient.getLock(lockKey);

        // 1. 尝试获取锁
        boolean success = lock.tryLock(redisLock.waitTime(), redisLock.leaseTime(), TimeUnit.SECONDS);
        if (!success) {
            throw new RuntimeException("系统繁忙，请稍后再试");
        }

        try {
            // 2. 执行后续逻辑（此处会进入下一个切面，即事务切面）
            return joinPoint.proceed();
        } finally {
            // 3. 释放锁
            // 确保只有持有锁的线程才能释放锁
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }
}
