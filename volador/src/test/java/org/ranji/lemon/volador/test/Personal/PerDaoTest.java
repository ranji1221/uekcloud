package org.ranji.lemon.volador.test.Personal;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.subject.Subject;
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
	public void functionTest(){
//		Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
//		Date date = cal.getTime();
//		cal.set(2011,6,6);
//		//通过格式化输出日期    
//		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");    
//
//		System.out.println("Today is:"+format.format(Calendar.getInstance().getTime()));    
//
//		System.out.println("new day is:"+format.format(cal.getTime()));
//		Per user = userservice.findByUserName("18193587628");
//		user.setPassword("123456");
//		userservice.update(user);
//		user = userservice.findByUserName("18193587628");
//		System.out.println("18193587628密码："+user.getPassword());
//		
//		user = userservice.findByUserName("18192903475");
//		user.setPassword("123456");
//		userservice.save(user);
//		user = userservice.findByUserName("18192903475");
//		System.out.println("18192903475密码："+user.getPassword());

//		System.out.println("新密码："+new DefaultPasswordService().encryptPassword("123456"));
				
		
	}
}
