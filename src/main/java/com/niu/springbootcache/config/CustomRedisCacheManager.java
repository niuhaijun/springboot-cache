package com.niu.springbootcache.config;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

//		// value的序列化与反序列化
//		Jackson2JsonRedisSerializer<Object> serializer =
//			new Jackson2JsonRedisSerializer<>(Object.class);
//

		Jackson2JsonRedisSerializer<Object> serializer =
			new Jackson2JsonRedisSerializer<>(Object.class);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		// 此项必须配置，否则会报java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to XXX
		objectMapper
			.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		serializer.setObjectMapper(objectMapper);

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
