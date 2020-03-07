package com.niu.springbootcache.config;

import com.niu.springbootcache.utils.BeanHelper;
import com.niu.springbootcache.utils.JsonHelper;
import java.lang.reflect.Method;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

/**
 * 自定义Spring cache Key生成器
 */
//@Component("customCacheKeyGenerator")
public class CustomCacheKeyGenerator implements KeyGenerator {

	/**
	 * 无参数的Key
	 */
	private static final int NO_PARAM_KEY = 0;
	/**
	 * key前缀，用于区分不同项目的缓存，建议每个项目单独设置
	 */
	private static final String KEY_PREFIX = "spring_cache_key_prefix";
	/**
	 * 分隔符
	 */
	private static final char Separator = ':';

	@Override
	public Object generate(Object target, Method method, Object... params) {

		StringBuilder strBuilder = new StringBuilder(30);

		strBuilder.append(KEY_PREFIX);
		strBuilder.append(Separator);
		// 类名
		strBuilder.append(target.getClass().getSimpleName());
		strBuilder.append(Separator);
		// 方法名
		strBuilder.append(method.getName());
		strBuilder.append(Separator);
		// 参数
		if (params.length > 0) {
			for (Object object : params) {
				if (BeanHelper.isSimpleValueType(object.getClass())) {
					strBuilder.append(object);
				}
				else {
					strBuilder.append(JsonHelper.toJson(object).hashCode());
				}
			}
		}
		else {
			strBuilder.append(NO_PARAM_KEY);
		}

		return strBuilder.toString();
	}
}
