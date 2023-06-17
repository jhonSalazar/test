package com.inclusion.cloud.service;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class CacheService {
    private final CacheManager cacheManager;
    public CacheService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }
    @Scheduled(fixedRate = 7200000)
    public void evictAllCaches() {
        cacheManager.getCacheNames().stream()
                .forEach(cacheName -> cacheManager.getCache(cacheName).clear());
    }
}
