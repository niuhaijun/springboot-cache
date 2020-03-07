## 参考链接
- [Spring Cache Redis 修改序列化方式](https://www.cnblogs.com/niugang0920/p/12186495.html)
- [spring-cache -KeyGenerator自定义](https://www.jianshu.com/p/6ba2d2dbf36e)
---

## spring cache注解
- @Cacheable
```text
```
- @CacheEvict
```text
```
- @CacheConfig
```text
```
- @Caching
```text
```
- CachePut
```text
```
---


## 自定义Key生成器
```text
实现 KeyGenerator 接口，重写generate方法。

spring cache缓存的key默认是通过KeyGenerator生成的，其默认生成策略如下：
  如果方法没有参数，则使用0作为key。
  如果只有一个参数的话则使用该参数作为key。
  如果参数多于一个的话则使用所有参数的hashCode作为key。
```
---


## 统一设置过期时间
```text
配置CacheManager，统一设置Key的过期时间
```
---