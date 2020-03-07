package com.niu.springbootcache.config;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

import java.time.Duration;
import javax.annotation.Resource;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * 替换 CacheConfig
 */
@Configuration
public class CustomRedisCacheManager extends CachingConfigurerSupport {

	private static final Duration TTL = Duration.ofSeconds(100);

	@Resource
	private RedisConnectionFactory connectionFactory;

	@Bean
	public RedisCacheConfiguration redisCacheConfiguration() {

		// value的序列化与反序列化
		Jackson2JsonRedisSerializer<Object> serializer =
			new Jackson2JsonRedisSerializer<>(Object.class);

		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
		configuration = configuration
			.entryTtl(TTL)
			.serializeValuesWith(fromSerializer(serializer));

		return configuration;
	}


	@Bean
	@Override
	public CacheManager cacheManager() {

		return RedisCacheManager
			.builder(connectionFactory)
			.cacheDefaults(redisCacheConfiguration())
			.build();
	}

	@Bean("customCacheKeyGenerator")
	@Override
	public KeyGenerator keyGenerator() {

		return new CustomCacheKeyGenerator();
	}
}
