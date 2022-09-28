package com.wixia.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * https://stackoverflow.com/questions/26021991/spring-redis-error-handle
 * <p>
 * Cache properties
 * https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties.cache
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport implements CachingConfigurer {

/*
    @Value("${redis.hostname:localhost}")
    private String redisHost;

    @Value("${redis.port:6379}")
    private int redisPort;

    @Value("${redis.timeout.secs:1}")
    private int redisTimeoutInSecs;

    @Value("${redis.socket.timeout.secs:1}")
    private int redisSocketTimeoutInSecs;

    @Value("${redis.ttl.hours:1}")
    private int redisDataTTL;
*/

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
            .withCacheConfiguration("itemCache",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
            .withCacheConfiguration("customerCache",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)));
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(60))
            .disableCachingNullValues()
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                new GenericJackson2JsonRedisSerializer()));
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new RedisCacheErrorHandler();
    }
}
