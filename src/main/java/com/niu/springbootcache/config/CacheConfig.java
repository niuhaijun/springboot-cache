//package com.niu.springbootcache.config;
//
//import java.time.Duration;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.cache.RedisCacheConfiguration;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//
//@Configuration
//public class CacheConfig {
//
//	private static final Duration TTL = Duration.ofMinutes(10);
//
//	@Bean
//	@Primary
//	public RedisCacheConfiguration redisCacheConfiguration() {
//
//		Jackson2JsonRedisSerializer<Object> serializer =
//			new Jackson2JsonRedisSerializer<>(Object.class);
//
//		RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
//		configuration = configuration
//			// 设置key的过期时间
//			.entryTtl(TTL)
//			// 配置value的序列化与反序列化
//			.serializeValuesWith(
//				RedisSerializationContext.SerializationPair
//					.fromSerializer(serializer));
//		return configuration;
//	}
//
//	@Bean
//	@Primary
//	public CacheManager cacheManager(
//		@Autowired RedisCacheConfiguration redisCacheConfiguration,
//		@Autowired RedisConnectionFactory connectionFactory) {
//
//		return RedisCacheManager
//			.builder(connectionFactory)
//			.cacheDefaults(redisCacheConfiguration)
//			.build();
//	}
//}
