package com.niu.springbootcache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动缓存支持
 */
@EnableCaching
@MapperScan("com.niu.springbootcache.mapper")
@SpringBootApplication
public class SpringbootCacheApplication {

  public static void main(String[] args) {

    SpringApplication.run(SpringbootCacheApplication.class, args);
  }

}