package com.inclusion.cloud.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableCaching
public class CacheConfig {
    public static final String CACHE_KEY_RESULT_MAXIMUM = "result";
    public static final List<String> caches = List.of(CACHE_KEY_RESULT_MAXIMUM);

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager mgr = new ConcurrentMapCacheManager();
        mgr.setCacheNames(caches);
        return mgr;
    }
}
