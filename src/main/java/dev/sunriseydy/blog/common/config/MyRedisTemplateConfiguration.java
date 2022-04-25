package dev.sunriseydy.blog.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author SunriseYDY
 * @date 2022-03-18 17:09
 */
@Configuration(proxyBeanMethods = false)
public class MyRedisTemplateConfiguration {

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

    public MyRedisTemplateConfiguration(ObjectMapper objectMapper) {
        this.jacksonSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        this.jacksonPair = RedisSerializationContext.SerializationPair.fromSerializer(jacksonSerializer);
    }

    @Bean
    public RedisTemplate<String, ?> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ?> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setValueSerializer(this.jacksonSerializer);
        template.setKeySerializer(this.stringSerializer);
        template.setHashKeySerializer(this.stringSerializer);
        template.setHashValueSerializer(this.jacksonSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
