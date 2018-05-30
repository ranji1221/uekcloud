package org.ranji.lemon.volador.controller.personal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Homework;
import org.ranji.lemon.volador.model.global.Notification;
import org.ranji.lemon.volador.model.personal.Integral;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.course.prototype.IHomeworkService;
import org.ranji.lemon.volador.service.global.prototype.INotificationService;
import org.ranji.lemon.volador.service.personal.prototype.IIntegralService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.ISignInService;
import org.ranji.lemon.volador.service.personal.prototype.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonalCenterController {
	
	@Autowired
	private IPerService personalService;
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IUserInfoService userInfoService ;
	
	@Autowired
	private IHomeworkService homeworkService ;
	
	@Autowired
	private ISignInService signInService;
	
	@Autowired
	private IIntegralService integralService;
	
	@Autowired
	private INotificationService inotificationService;
	
	//用户签到获得的积分
	private Integer SIGNIN_INTRGRAL_NUM = 20;
	
	/*
	 * 个人中心 - 通知公告
	 * */
	@RequestMapping(value="/personalCenter_notice", method=RequestMethod.GET)
	public ModelAndView personalCenterNotice(){
		ModelAndView mv = new ModelAndView();
		List<Notification> notificationList = inotificationService.findAll();
		mv.addObject("notificationList", notificationList);
		mv.addObject("notificationNumber", notificationList.size());
		//System.out.println(notificationList.get(0).getCreateTime());
		 
	     
		mv.setViewName("/backend/wqf_notice");
		return mv;
	}
	
	/*
	 * 个人中心 - 评论 
	 * */
	@RequestMapping(value="/personalCenter_comment", method=RequestMethod.GET)
	public ModelAndView personalCenterComment(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_comment");
		return mv;
	}
	
	
	/*
	 * 个人中心 - 正在学习的课程 
	 * */
	@RequestMapping(value="/personalCenter_learning", method=RequestMethod.GET)
	public ModelAndView personalCenterLearn_now(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		//异常处理，当没有获取到登录信息时，跳转到登录页面
		try {
			//根据session获取userId，查询正在学习课程
			int userId=(int) request.getSession().getAttribute("userId");
			
			//获取参数page,如果为空，page=1
			int page;
			if(request.getParameter("page") == null){
				 page= 1;
			}else{
				page=Integer.parseInt(request.getParameter("page"));
			}
			
			//查询相应页数Course列表，及总条数
			PagerModel<Course> pageCourse=personalService.findPageStudyingCourseByUser(userId, page, 3);

			//将course列表及分页信息返回
			List<Course> courseList = pageCourse.getData();
			mv.addObject(courseList);
			mv.addObject("TotalNumber",pageCourse.getTotal());
			mv.addObject("currentPage",page);
			mv.addObject("pageCount",pageCourse.getTotal()/3);
			mv.addObject("interfaceName","personalCenter_learning");
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			mv.addObject("address",userInfo.getAddress());
			
			//查询学习时长
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
			
			mv.setViewName("/backend/wqf_learn_now");
		} catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	/*
	 * 用户签到
	 */
	@RequestMapping(value="/personSignIn", method=RequestMethod.GET)
	public ModelAndView personSignIn(HttpServletRequest request){
		ModelAndView mv =new ModelAndView();
		try{
			
			int userId = (int) request.getSession().getAttribute("userId");
			
			//获取用户签到表
			SignIn signIn = signInService.findSignInByUserId(userId);
			//获取用户积分表
			Integral integral = integralService.findIntegralByUserId(userId);
			
			/*判断用户是否已经签到*/
			//日期格式化
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
			
			//格式化需要判断的日期
			String strNeedCheckDate = simpleDateFormat.format(signIn.getUpdateTime());
			
			//格式化当前日期
			Date currentDate = new Date();
			String strCurrentDate = simpleDateFormat.format(currentDate);
			
			//比较是否在同一天
			if(!strNeedCheckDate.equals(strCurrentDate)){
				//如果不在同一天，用户签到
				signIn.setUpdateTime(currentDate);
				signIn.setDay(signIn.getDay() + 1);
				signInService.update(signIn);
				
				//积分增加20 
				integral.setUpdateTime(currentDate);
				integral.setIntegralNumber(integral.getIntegralNumber()+SIGNIN_INTRGRAL_NUM);
				integralService.update(integral);
			}
			
			mv.setViewName("redirect:/index");
			
		} catch (Exception e){
			e.printStackTrace();
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	/*
	 * 个人中心 - 已学习完的课程 
	 * */
	@RequestMapping(value="/personalCenter_learn_end", method=RequestMethod.GET)
	public ModelAndView personalCenterLearn_end(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		//异常处理，当没有获取到登录信息时，跳转到登录页面
		try {
			//根据session获取userId，查询已学习，并查询当前用户信息
			int userId=(int) request.getSession().getAttribute("userId");
			//获取参数page,如果为空，page=1
			int page;
			if(request.getParameter("page") == null){
				page=1;
			}else{
				page=Integer.parseInt(request.getParameter("page"));
			}
			
			//查询相应页数Course列表及总条数
			PagerModel<Course> pageCourse=personalService.findPageFinishCourseByUser(userId, page, 6);

			//将course列表及分页信息返回
			List<Course> courseList = pageCourse.getData();
			mv.addObject(courseList);
			mv.addObject("TotalNumber",pageCourse.getTotal());
			mv.addObject("currentPage",page);
			mv.addObject("pageCount",(pageCourse.getTotal()/6)+1);
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			mv.addObject("address",userInfo.getAddress());
			mv.addObject("interfaceName","personalCenter_learn_end");
			
			//查询学习时长
			
			
			//查询我的积分
			
			
			//查询签到天数
			
			mv.setViewName("/backend/wqf_learn_end");
		} catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	/*
	 * 个人中心 - 收藏课程 
	 * */
	@RequestMapping(value="/personalCenter_collect_course", method=RequestMethod.GET)
	public ModelAndView personalCenterFiavourite_course(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		//异常处理，当没有获取到登录信息时候，跳转到登录页面
		try {
			int userId=(int) request.getSession().getAttribute("userId");
			int page;
			if(request.getParameter("page") == null){
				page=1;
			}else{
				page=Integer.parseInt(request.getParameter("page"));
			}
			
			//查询相应页数Course列表及总条数
			PagerModel<Course> pageCourse=personalService.findPageCollectCourseByUser(userId, page, 3);

			//将course列表及分页信息返回
			List<Course> courseList = pageCourse.getData();
			mv.addObject(courseList);
			mv.addObject("TotalNumber",pageCourse.getTotal());
			mv.addObject("currentPage",page);
			mv.addObject("pageCount",(pageCourse.getTotal()/3)+1);
			
			//查询当前用户信息，并传递页面需要的用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			mv.addObject("address",userInfo.getAddress());
			mv.setViewName("/backend/wzq_fiavourite_end");
		} catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	/*
	 * 个人中心 - 资料下载 
	 * */
	@RequestMapping(value="/personalCenter_data_download", method=RequestMethod.GET)
	public ModelAndView personalCenterData_download(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try {
			//获取userId
			int userId=(int) request.getSession().getAttribute("userId");
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			mv.addObject("address",userInfo.getAddress());
			
			mv.setViewName("/backend/wqf_data_download");
			
		} catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}

		

		return mv;
	}
	
	/*
	 * 个人中心 - 积分页面
	 * */
	@RequestMapping(value="/personalCenter_jifen", method=RequestMethod.GET)
	public ModelAndView personalCenterJifen(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try {
			int userId=(int) request.getSession().getAttribute("userId");
			mv.setViewName("/backend/wzq_jifen");
		} catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		
		return mv;
	}
	
	/*
	 * 个人中心 - 积分抽奖规则 
	 * */
	@RequestMapping(value="/personalCenter_draw_rule", method=RequestMethod.GET)
	public ModelAndView personalCenterDraw_rule(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_draw_rule");
		return mv;
	}
	
	/*
	 * 个人中心 - 我的作业
	 * */
	@RequestMapping(value="/personalCenter_homework", method=RequestMethod.GET)
	public ModelAndView personalCenterHomework(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try {
			int userId=(int) request.getSession().getAttribute("userId");
			List<Integer> homeworkIdList=personalService.findHomeworkRelationByUserId(userId);
			List<Homework> homeworkList=new ArrayList<Homework>();
			for(int i=0;i<homeworkIdList.size();i++){
				//当查询信息为空时，异常处理
				try {
					homeworkList.add(homeworkService.find(homeworkIdList.get(i)));
				}catch (NullPointerException e) {
					// TODO: handle exception
					break;
					}
			}
			mv.addObject(homeworkList);
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			mv.addObject("address",userInfo.getAddress());
			
			mv.setViewName("/backend/wzq_list_work_upload");
		} catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}

		return mv;
	}
	
	/*
	 * 个人中心 - 跳转到作业上传页面
	 * */
	@RequestMapping(value="/personalCenter_uploadHomework", method=RequestMethod.GET)
	public ModelAndView uploadHomeworkPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		//判断是否登录
		try {
			int userId=(int) request.getSession().getAttribute("userId");
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			mv.addObject("address",userInfo.getAddress());
			
			
			
			mv.setViewName("/backend/wqf_upload_work");
		} catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	/*
	 * 个人中心 - 作业上传
	 * */
	@RequestMapping(value="/personalCenter_uploadHomework", method=RequestMethod.POST)
	public ModelAndView uploadHomework(			
			@RequestParam(value="pic",required=false) MultipartFile file,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="info",required=false) String info){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_upload_work");
		return mv;
	}
	
	/*
	 * 个人中心 - 职业导航
	 * */
	@RequestMapping(value="/personalCenter_zhiye", method=RequestMethod.GET)
	public ModelAndView personalCenterZhiye(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wzq_zhiye");
		return mv;
	}
	
	/*
	 * 个人中心 - 作业展示
	 * */
	@RequestMapping(value="/personalCenter_showHomework", method=RequestMethod.GET)
	public ModelAndView personalCenterShowHomework(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wzq_show_work");
		return mv;
	}
	
	
	/*
	 * 个人中心 - 邀请码
	 * */
	@RequestMapping(value="/personalCenter_invitation", method=RequestMethod.GET)
	public ModelAndView personalCenterInvitation(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_invitation");
		return mv;
	}
	
	
	
	/*
	 * 个人中心 - 清空里面查询全部通知/公告
	 * */
	@RequestMapping(value="/personalCenter_allnotice", method=RequestMethod.GET)
	public ModelAndView personalAllCenterNotice(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		List<Notification> notificationList = inotificationService.findAll();
		mv.addObject("notificationList", notificationList);
		mv.addObject("notificationNumber", notificationList.size());
		 int notificationNumber = inotificationService.getTotalOfItems();
		 int userId=(int) request.getSession().getAttribute("userId");
		  Map map = new HashMap();
          map.put("userId", userId);
          map.put("ignoreNotificationNumber", notificationNumber);
          Map existMap = inotificationService.findIgnoreNotificationByUser(userId);
          if(existMap!=null&&!existMap.isEmpty()){
          	
        	  inotificationService.updateIgnoreNotNum(map);
          }else{
        	  inotificationService.saveUserAndIgnoreNotificationRelation(userId, notificationNumber);
          }
		//System.out.println(notificationList.get(0).getCreateTime());
		mv.setViewName("/backend/wqf_notice");
		return mv;
	}
	

}
