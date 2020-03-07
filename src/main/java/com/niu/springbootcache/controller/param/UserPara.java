package com.niu.springbootcache.controller.param;

import java.util.Date;
import lombok.Data;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-11 15:18
 * @Version 1.0
 */
@Data
public class UserPara {

	private String uuid;

	private String username;

	private String password;

	private Integer age;

	private Integer sex;

	private Date createTime;

	private Date updateTime;

}
