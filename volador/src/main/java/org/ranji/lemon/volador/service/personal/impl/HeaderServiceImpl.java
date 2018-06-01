package org.ranji.lemon.volador.service.personal.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.global.prototype.INotificationService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.ISignInService;
import org.ranji.lemon.volador.service.personal.prototype.IheaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service("VoladorHeaderServiceImpl")
public class HeaderServiceImpl implements IheaderService {
	@Autowired
	private   IPerService personalService;
	@Autowired
	private   INotificationService notificationService;
	@Autowired
	private   ISignInService signInService;
	@Override
	public ModelAndView headInfo(int userId, String userName) {
		ModelAndView mv = new ModelAndView();
		int notificationSize = 0;
		List<Map> notificationList = new ArrayList<>();
		//检测是否登录
		try{
			//登录成功，返回userName及head_image
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("head_image",userInfo.getHead_image());
			mv.addObject("headLogin_yes","login_yes active");
			mv.addObject("headLogin_no","login_no");
			mv.addObject("headUserName", userName);
			
			//查看用户是否已经签到
			//获取用户签到表
			SignIn signIn = signInService.findSignInByUserId(userId);
			//日期格式化
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			
			//格式化需要判断的日期
			String strNeedCheckDate = simpleDateFormat.format(signIn.getUpdateTime());
			
			//格式化当前日期
			Date currentDate = new Date();
			String strCurrentDate = simpleDateFormat.format(currentDate);
			
			//比较是否在同一天
			if(strNeedCheckDate.equals(strCurrentDate)){
				//如果是同一天说明用户已经签到
				mv.addObject("headIsSignIn", "done");
			}else{
				mv.addObject("headIsSignIn", "no");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			mv.addObject("login_yes","login_yes");
			mv.addObject("login_no","login_no active");
		}
		
		//空指针异常处理，当未获取到数据时候，前台显示资料为空
		try {
				
			//绑定用户未忽略的信息
			
			int ignoreNitificationNumber = 0;
			Map map = notificationService.findIgnoreNotificationByUser(userId);
			if(map!=null&&!map.isEmpty()){
				ignoreNitificationNumber = Integer.parseInt(map.get("ignore_notification_number").toString());
			}
			int startIgnNotificationNumber = ignoreNitificationNumber+1;
			int endIgnNotificationNumber = notificationService.getTotalOfItems();
			notificationList = notificationService.findTop3Notification(startIgnNotificationNumber, endIgnNotificationNumber);
			notificationSize = notificationService.notReadNumber(startIgnNotificationNumber, endIgnNotificationNumber);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		mv.addObject("headnotificationList", notificationList);
		mv.addObject("headnotificationSize", notificationSize);
		mv.addObject("userId", userId);
		return mv;
	}
	

}
