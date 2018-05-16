package org.ranji.lemon.volador.test.Personal;

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
public class UserInfoDaoTest {
	@Autowired
	private IPerService userservice;
	@Autowired
	private IUserInfoService userinfoservice;
	
	private String userName;
	private Integer userInfoId;
	private Integer userId;

	@Test
	public void addUserInfo(){
		UserInfo userinfo=new UserInfo();
		userinfo.setAddress("上海");
//		userinfo.setHead_image("IMG_0011.JPG");
//		userinfo.setEmail("1056684096@qq.com");
//		userinfo.setGender("woman");
//		userinfo.setIdcard("620123489632514785");
//		userinfo.setNickname("修改前");
		userinfo.setQQ("1056684096");
		userinfo.setReal_name("真实姓名");
		userinfo.setWechat("wechat");
		userinfoservice.saveUserInfo(userinfo);
		userInfoId = userinfo.getId();
		System.out.println( "add UserInfo,ID: "+ userInfoId);
	}
	@Test
	public void updateUserInfo(){
		UserInfo userinfo = userinfoservice.find(userInfoId);
		userinfo.setNickname("修改后");
		userinfoservice.update(userinfo);
		System.out.println( "update UserInfo, ID: "+ userInfoId);
	}
	@Test
	public void deleteUserInfo(){
		userinfoservice.delteUserInfoByUserInfoId(userInfoId);
		System.out.println( "delete UserInfo, ID: "+ userInfoId);
	}
	

}
