package com.niu.springbootcache.service.impl;


import com.niu.springbootcache.controller.param.UserPara;
import com.niu.springbootcache.controller.vo.UserVO;
import com.niu.springbootcache.mapper.UserMapper;
import com.niu.springbootcache.model.User;
import com.niu.springbootcache.model.UserExample;
import com.niu.springbootcache.service.UserService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override

  @Caching(
      evict = {
          @CacheEvict(value = "user", key = "#userPara.age()", beforeInvocation = true, allEntries = true)})
  public Integer add(UserPara userPara) {

    Date date = new Date();
    userPara.setCreateTime(date);
    userPara.setUpdateTime(date);

    User user = new User();
    BeanUtils.copyProperties(userPara, user);
    return userMapper.insertSelective(user);
  }

  @Override
  @CacheEvict(value = "user", allEntries = true)
  public Integer update(UserPara userPara) {

    userPara.setUpdateTime(new Date());
    User user = new User();
    BeanUtils.copyProperties(userPara, user);

    UserExample example = new UserExample();
    example.createCriteria().andUuidEqualTo(userPara.getUuid());
    return userMapper.updateByExampleSelective(user, example);
  }

  @Override
  @CacheEvict(value = "user", allEntries = true)
  public Integer delete(UserPara userPara) {

    return userMapper.deleteByPrimaryKey(userPara.getUuid());
  }

  @Override
  @Cacheable(value = "user", key = "#userPara.age")
  public List<UserVO> select(UserPara userPara) {

    UserExample example = new UserExample();
    example.setOrderByClause("update_time desc");
    example.createCriteria().andAgeLessThan(userPara.getAge());

    List<User> userList = userMapper.selectByExample(example);
    List<UserVO> result = new ArrayList<>(userList.size());
    userList.forEach(t -> {
      UserVO userVO = new UserVO();
      BeanUtils.copyProperties(t, userVO);
      result.add(userVO);
    });

    return result;
  }

}