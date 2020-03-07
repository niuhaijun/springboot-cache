package com.niu.springbootcache.controller.vo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-11 15:19
 * @Version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {

	private String uuid;
	private String username;
	private String password;
	private Integer age;
	private Integer sex;
	private Date createTime;
	private Date updateTime;
}
