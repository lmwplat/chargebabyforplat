package com.charge.test;

import com.charge.config.vo.Json;
import com.charge.model.User;
import com.charge.service.FavoriteServiceI;
import com.charge.service.FeedbackServiceI;
import com.charge.service.UserServiceI;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.alibaba.fastjson.JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestMybatis {

	private static final Logger logger = Logger.getLogger(TestMybatis.class);

	private UserServiceI userService;
	@Autowired
	private FavoriteServiceI favoriteService;

	public UserServiceI getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserServiceI userService) {
		this.userService = userService;
	}

	/*@Test
	public void test1() {
		User u = userService.getUserById(1l);
		logger.info(JSON.toJSONStringWithDateFormat(u, "yyyy-MM-dd HH:mm:ss"));
	}

	@Test
	public void testLogin() {
		User user = userService.login("lmw", "123456");
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}*/

	/*@Test
	public void testGetUserByName() {
		User user = userService.se("lmw", "123456");
		logger.info(JSON.toJSONStringWithDateFormat(user, "yyyy-MM-dd HH:mm:ss"));
	}*/

/*	@Test
	public void test4() {
		List<User> l = userService.getAll3();
		logger.info(JSON.toJSONStringWithDateFormat(l, "yyyy-MM-dd HH:mm:ss"));
	}*/

/*	@Test
	public void findFavorite(){
		Json json = favoriteService.findFavorite();
		logger.info(JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss"));
	}*/

	@Test
	public void addFavorite() throws Exception {
		Json json = favoriteService.addFavorite(39L, "15");
		logger.info(JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss"));
	}
}
