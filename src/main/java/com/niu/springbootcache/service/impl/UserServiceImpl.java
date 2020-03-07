package com.niu.springbootcache.service.impl;


import com.niu.springbootcache.controller.param.UserPara;
import com.niu.springbootcache.controller.vo.UserVO;
import com.niu.springbootcache.mapper.UserMapper;
import com.niu.springbootcache.model.User;
import com.niu.springbootcache.model.UserExample;
import com.niu.springbootcache.service.UserService;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-11 15:14
 * @Version 1.0
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "users", keyGenerator = "customCacheKeyGenerator")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	@Caching(
		evict = {
			@CacheEvict(key = "#userPara.age()", beforeInvocation = true)})
	public Integer add(UserPara userPara) {

		Date date = new Date();
		userPara.setCreateTime(date);
		userPara.setUpdateTime(date);

		User user = new User();
		try {
			BeanUtils.copyProperties(userPara, user);
		}
		catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return userMapper.insertSelective(user);
	}

	@Override
	@CacheEvict(allEntries = true)
	public Integer update(UserPara userPara) {

		userPara.setUpdateTime(new Date());
		User user = new User();
		try {
			BeanUtils.copyProperties(userPara, user);
		}
		catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		UserExample example = new UserExample();
		example.createCriteria().andUuidEqualTo(userPara.getUuid());
		return userMapper.updateByExampleSelective(user, example);
	}

	@Override
	@CacheEvict(allEntries = true)
	public Integer delete(UserPara userPara) {

		return userMapper.deleteByPrimaryKey(userPara.getUuid());
	}

	@Override
//	@Cacheable(value = "users", key = "#userPara.age")
	@Cacheable(value = "users", keyGenerator = "customCacheKeyGenerator")
	public List<UserVO> select(UserPara userPara) {

		UserExample example = new UserExample();
		example.createCriteria()
			.andAgeLessThan(userPara.getAge());

		try {
			Thread.currentThread().sleep(1000);
		}
		catch (InterruptedException e) {
			log.error("error", e);
		}

		List<User> userList = userMapper.selectByExample(example);
		List<UserVO> result = new ArrayList<>(userList.size());
		userList.forEach(t -> {
			UserVO userVO = new UserVO();
			try {
				BeanUtils.copyProperties(userVO, t);
			}
			catch (IllegalAccessException | InvocationTargetException e) {
				log.error("error", e);
			}
			result.add(userVO);
		});

		return result;
	}

}
