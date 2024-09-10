package com.northcoders.record_shop;

import com.northcoders.record_shop.model.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.management.timer.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@EnableCaching
public class CachingConfig {

    public static final String ALBUMS_CACHE = "albums";
    private static final Logger LOGGER = LoggerFactory.getLogger(CachingConfig.class);

    @Scheduled(fixedRate = Timer.ONE_HOUR)
    @CacheEvict(value = ALBUMS_CACHE, allEntries = true)
    public void clearAlbums(){
        LOGGER.info("Clearing album caches");
    }

    @Autowired
    CacheManager cacheManager;

    public void evictSingleCacheValue(String cacheKey){
        cacheManager.getCache(ALBUMS_CACHE).evict(cacheKey);
    }

}
