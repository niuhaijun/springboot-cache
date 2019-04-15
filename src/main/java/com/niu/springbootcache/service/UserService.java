package com.niu.springbootcache.service;

import com.niu.springbootcache.controller.param.UserPara;
import com.niu.springbootcache.controller.vo.UserVO;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-11 15:14
 * @Version 1.0
 */
@Transactional
public interface UserService {

  Integer add(UserPara userPara);

  Integer update(UserPara userPara);

  Integer delete(UserPara userPara);

  List<UserVO> select(UserPara userPara);
}
