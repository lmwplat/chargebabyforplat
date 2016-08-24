package com.charge.test;

import com.alibaba.fastjson.JSONArray;
import com.charge.config.vo.Datagrid;
import com.charge.config.vo.Json;
import com.charge.model.User;
import com.charge.service.admin.UserAdminServiceI;
import com.charge.service.front.CollectServiceI;
import com.charge.service.front.CommentServiceI;
import com.charge.service.front.FavoriteServiceI;
import com.charge.service.front.UserServiceI;
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
	@Autowired
	private CollectServiceI collectService;
	@Autowired
	private CommentServiceI commentService;
	@Autowired
	private UserAdminServiceI userAdminService;

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

	/*@Test
	public void addFavorite() throws Exception {
		Json json = favoriteService.addFavorite(39L, "15");
		logger.info(JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss"));
	}*/

	/*@Test
	public void addCollect(){
		Json json = collectService.addCollect("lmw", "广东省广州市越秀区");
		logger.info(JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss"));
	}*/

	@Test
	public void	addComment() throws Exception {
		Json json = commentService.addComment("45", 39L, "lmw", "222222这个充电桩不错！！！！nice");
		logger.info(JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss"));
	}

	@Test
	public void addReply() throws Exception{
		Json json = commentService.addReply(8L, "第2次，是不是这样！！！！nice", 40L, "liumw",null, null );
		logger.info(JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss"));
	}

	@Test
	public void findAllComment() throws Exception{
		Json json = commentService.findAllComment("45" );
		logger.info(JSON.toJSONStringWithDateFormat(json, "yyyy-MM-dd HH:mm:ss"));

		/*String jsonString = JSONArray.toJSONString(json.getObj());
		List<CommentVo> list=new ArrayList<CommentVo>(JSONArray.parseArray(jsonString,CommentVo.class));
		logger.info(list.toString());
		List<CommentVo> all=new ArrayList<CommentVo>();
		all = JSONArray.parseArray(jsonString, CommentVo.class);*/
		/*for (Object comment : noArr) {
			all.add((CommentVo) comment);
		}*/
		String jsonString = JSON.toJSONString(json.getObj());
	//	List<CommentVo> list2 = JSON.parseObject(jsonString,new TypeReference<List<CommentVo>>(){});
		//logger.info(all.toString());

		String str = JSONArray.toJSONString(json.getObj());
		JSONArray array = JSONArray.parseArray(str);
		for (Object o: array) {
			logger.info(o.toString());

		}

		/*JSONArray array = JSONArray.parseArray(str);
		list2 = (List<CommentVo>) array.listIterator();*/

//		List<CommentVo> list2 = (List<CommentVo>) json.getObj();
	}


	@Test
	public void datGridForUser() throws Exception {
		Datagrid<User> user = userAdminService.dataGrid(2,10);
		logger.info(user.toString());
	}

}
