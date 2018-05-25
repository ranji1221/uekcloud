package org.ranji.lemon.volador.test.Personal;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.core.util.DateUtil;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.persist.auth.prototype.IUserDao;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class PerDaoTest {
	@Autowired
	private IPerService userservice;
	@Autowired
	private IUserInfoService userinfoservice;
	
	private String userName;
	private Integer userInfoId;
	private Integer userId;

	/**
	@Test
	public String addUser(){
		Per user = new Per();    
		user.setUsername("add4");
		user.setPassword("123456");
		userservice.save(user);
		userName = user.getUsername();
		userId = user.getId();
		return "add User: "+ userName;
	}
	
	@Test
	public String updateUser(){
		Per user = userservice.findByUserName(userName);
		user.setUpdateTime(DateUtil.now());
		System.out.print(user.getUpdateTime());
		userservice.update(user);
		return "update User: "+ userName;
	}
	
	@Test
	public String deleteUser(){
		Per user = userservice.findByUserName(userName);
		userservice.delete(user.getId());
		return "delete User: "+ userName;
	}

	@Test
	public String addUserInfo(){
		UserInfo userinfo=new UserInfo();
		userinfo.setAddress("上海");
		userinfo.setHead_image("IMG_0011.JPG");
		userinfo.setEmail("1056684096@qq.com");
		userinfo.setGender("woman");
		userinfo.setIdcard("620123489632514785");
		userinfo.setNickname("修改前");
		userinfo.setQQ("1056684096");
		userinfo.setReal_name("真实姓名");
		userinfo.setWechat("wechat");
		userinfoservice.saveUserInfo(userinfo);
		userInfoId = userinfo.getId();
		return "add UserInfo,ID: "+ userInfoId;
	}
	@Test
	public String updateUserInfo(){
		UserInfo userinfo = userinfoservice.find(userInfoId);
		userinfo.setNickname("修改后");
		userinfoservice.update(userinfo);
		return "update UserInfo, ID: "+ userInfoId;
	}
	@Test
	public String deleteUserInfo(){
		userinfoservice.delteUserInfoByUserInfoId(userInfoId);
		return "delete UserInfo, ID: "+ userInfoId;
	}
	//测试用户和用户信息关系表操作
	@Test
	public String saveUserAndUserInfoRelation(){
		addUser();
		addUserInfo();
		userservice.saveUserAndUserInfoRelation(userId, userInfoId);
		return "saveUserAndUserInfoRelation";
	}
	
	@Test
	public String deleteUserAndUserInfoRelation(){
		addUser();
		addUserInfo();
		userservice.deleteUserAndUserInfoRelation(userId, userInfoId);
		return "deleteUserAndUserInfoRelation";
	}
	@Test
	public String deleteUserUserInfoByUserId(){
		userservice.deleteUserUserInfoByUserId(userservice.findAll().get(0).getId());
		return "deleteUserUserInfoByUserId UserInfoId: "+ userservice.findAll().get(0).getId();
	}
	@Test
	public String findUserUserInfoRelationByUserId(){
		String id = userservice.findUserUserInfoRelationByUserId(userservice.findAll().get(0).getId()).get(0).toString();
		return "findUserUserInfoRelationByUserId UserInfoId:" + id;
	}
	**/
	//测试用户和购买课程的关系表操作
//	@Test
//	public void saveUserAndBuyCourseRelation(){
//		userservice.saveUserAndBuyCourseRelation(0, 2);
//		System.out.println( "saveUserAndBuyCourseRelation");
//	}
//	
//	@Test
//	public void deleteBuyCourseRelationByUserId(){
//		userservice.deleteBuyCourseRelationByUserId(0);
//		System.out.println( "deleteBuyCourseRelationByUserId");
//	}
//	@Test
//	public void deleteBuyCourseRelation(){
//		userservice.deleteBuyCourseRelation(0,2);
//		System.out.println(  "deleteBuyCourseRelation ");
//	}
//	@Test
//	public void findBuyCourseRelationByUserId(){
//		List <Integer> lists = userservice.findBuyCourseRelationByUserId(0);
//		System.out.println( "findBuyCourseRelationByUserId CourseId:" + lists.toString());
//	}

	//测试用户和收藏课程的关系表操作
//	@Test
//	public void saveUserAndCollectCourseRelation(){
//		userservice.saveUserAndCollectCourseRelation(1, 1);
//		System.out.println( "saveUserAndCollectCourseRelation");
//	}
//	
//	@Test
//	public void deleteCollectCourseRelationByUserId(){
//		userservice.deleteCollectCourseRelationByUserId(1);
//		System.out.println( "deleteCollectCourseRelationByUserId");
//	}
//	@Test
//	public void deleteCollectCourseRelation(){
//		userservice.deleteCollectCourseRelation(1,1);
//		System.out.println(  "deleteCollectCourseRelation ");
//	}
//	@Test
//	public void findCollectCourseRelationByUserId(){
//		List <Integer> lists = userservice.findCollectCourseRelationByUserId(1);
//		System.out.println( "findCollectCourseRelationByUserId CourseId:" + lists.toString());
//	}
	
	//测试用户和正在学习课程的关系表操作
//	@Test
//	public void saveUserAndStudyingCourseRelation(){
//		userservice.saveUserAndStudyingCourseRelation(1, 2);
//		System.out.println( "saveUserAndStudyingCourseRelation");
//	}
//	
//	@Test
//	public void deleteStudyingCourseRelationByUserId(){
//		userservice.deleteStudyingCourseRelationByUserId(1);
//		System.out.println( "deleteStudyingCourseRelationByUserId");
//	}
//	@Test
//	public void deleteStudyingCourseRelation(){
//		userservice.deleteStudyingCourseRelation(1,1);
//		System.out.println(  "deleteStudyingCourseRelation ");
//	}
//	@Test
//	public void findStudyingCourseRelationByUserId(){
//		List <Integer> lists = userservice.findStudyingCourseRelationByUserId(1);
//		System.out.println( "findStudyingCourseRelationByUserId CourseId:" + lists.toString());
//	}
	
	//测试用户和学习完的课程的关系表操作
//	@Test
//	public void saveUserAndStudyedCourseRelation(){
//		userservice.saveUserAndStudyedCourseRelation(1, 1);
//		System.out.println( "saveUserAndStudyedCourseRelation");
//	}
//	
//	@Test
//	public void deleteStudyedCourseRelationByUserId(){
//		userservice.deleteStudyedCourseRelationByUserId(1);
//		System.out.println( "deleteStudyedCourseRelationByUserId");
//	}
//	@Test
//	public void deleteStudyedCourseRelation(){
//		userservice.deleteStudyedCourseRelation(1,1);
//		System.out.println(  "deleteStudyedCourseRelation ");
//	}
//	@Test
//	public void findStudyedCourseRelationByUserId(){
//		List <Integer> lists = userservice.findStudyedCourseRelationByUserId(1);
//		System.out.println( "findStudyedCourseRelationByUserId CourseId:" + lists.toString());
//	}
	
	//测试用户和作业的关系表操作
//	@Test
//	public void saveUserAndHomeworkRelation(){
//		userservice.saveUserAndHomeworkRelation(1, 2);
//		System.out.println( "saveUserAndHomeworkRelation");
//	}
	
//	@Test
//	public void deleteHomeworkRelationByUserId(){
//		userservice.deleteHomeworkRelationByUserId(1);
//		System.out.println( "deleteHomeworkRelationByUserId");
//	}
//	@Test
//	public void deleteHomeworkRelation(){
//		userservice.deleteHomeworkRelation(1,1);
//		System.out.println(  "deleteHomeworkRelation ");
//	}
//	@Test
//	public void findHomeworkRelationByUserId(){
//		List <Integer> lists = userservice.findHomeworkRelationByUserId(1);
//		System.out.println( "findHomeworkRelationByUserId HomeworkId:" + lists.toString());
//	}

	@Test
	public void findUserInfoByNoteId(){
		UserInfo userInfo = userservice.findUserInfoByNoteId(7);
		System.out.println( userInfo.toString());
	}
}
