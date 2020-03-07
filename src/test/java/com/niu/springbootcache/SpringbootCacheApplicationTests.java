package com.niu.springbootcache;

import com.alibaba.fastjson.JSON;
import com.niu.springbootcache.controller.param.UserPara;
import com.niu.springbootcache.controller.vo.UserVO;
import com.niu.springbootcache.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootCacheApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {

		UserPara userPara = new UserPara();
		userPara.setAge(100);

		long startTime = System.currentTimeMillis();
		List<UserVO> list = userService.select(userPara);
		log.info((System.currentTimeMillis() - startTime) + ": " + JSON.toJSONString(list));
	}

}
