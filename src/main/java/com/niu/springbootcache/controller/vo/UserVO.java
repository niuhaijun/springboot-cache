package com.niu.springbootcache.controller.vo;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import lombok.Data;

/**
 * @Author: niuhaijun
 * @Date: 2019-04-11 15:19
 * @Version 1.0
 */
@Data
@JsonInclude(value = NON_NULL)
public class UserVO {

	private String uuid;

	private String username;

	private String password;

	private Integer age;

	private Integer sex;

	private Date createTime;

	private Date updateTime;
}
