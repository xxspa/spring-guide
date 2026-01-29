package com.newzhxu.caching;

import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentMap;

@Configuration
@EnableCaching
public class CacheConfig {
    private static final Logger log = LoggerFactory.getLogger(CacheConfig.class);
    private final CacheManager cacheManager;

    public CacheConfig(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

//    @Bean
//    CacheManager cacheManager() {
//        SimpleCacheManager manager = new SimpleCacheManager();
//        this.cacheManager = manager;
//        ArrayList<Cache> caches = new ArrayList<>();
//        caches.add(new ConcurrentMapCache("books"));
//        manager.setCaches(caches);
//        return manager;
//    }

    @PreDestroy
    public void onDestroy() {
        Cache books = cacheManager.getCache("books");
        if (books == null) {
            log.info("books cache not found");
            return;
        }

        Book valueWrapper = books.get("isbn-1234", Book.class);
        log.info("books cache contains isbn-1234 value: {}", valueWrapper);
        Object nativeCache = books.getNativeCache();
        if (nativeCache instanceof ConcurrentMap concurrentMap) {
            concurrentMap.forEach((k, v) -> log.info("cache entry key={} value={}", k, v));
        } else {
            log.info("native cache type: {}, value: {}", nativeCache.getClass(), nativeCache);
        }


    }
}
