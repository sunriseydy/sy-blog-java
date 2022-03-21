package dev.sunriseydy.wp.common.config;

import dev.sunriseydy.wp.common.constants.WpCacheConstant;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author SunriseYDY
 * @date 2022-03-18 16:18
 */
@Configuration(proxyBeanMethods = false)
public class MyRedisCacheManagerConfiguration {

    /**
     * key serializer
     */
    private RedisSerializer<String> stringSerializer = RedisSerializer.string();

    /**
     * key serializer pair
     */
    private RedisSerializationContext.SerializationPair<String> stringPair =
            RedisSerializationContext.SerializationPair.fromSerializer(stringSerializer);
    /**
     * value serializer
     * <p>注意不能使用Jackson2JsonRedisSerializer,该类不能根据类型来反序列化，会出现类型转换的异常。
     */
    private final RedisSerializer<Object> jacksonSerializer;

    /**
     * value serializer pair
     */
    private final RedisSerializationContext.SerializationPair<Object> jacksonPair;

    public MyRedisCacheManagerConfiguration() {
        this.jacksonSerializer = new GenericJackson2JsonRedisSerializer();
        this.jacksonPair = RedisSerializationContext.SerializationPair.fromSerializer(jacksonSerializer);
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer myRedisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .prefixCacheNameWith(WpCacheConstant.CACHE_KEY_PREFIX)
                        .disableCachingNullValues()
                        .serializeKeysWith(stringPair)
                        .serializeValuesWith(jacksonPair)
                )
                .build();

    }

}
