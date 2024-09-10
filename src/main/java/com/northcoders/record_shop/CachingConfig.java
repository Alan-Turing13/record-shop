package com.northcoders.record_shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.management.timer.Timer;

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

    @Scheduled(fixedRate = Timer.ONE_MINUTE)
    @CacheEvict(value = ALBUMS_CACHE, allEntries = true)
    public void clearAlbums(){
        LOGGER.info("Clearing album caches");
    }

}
