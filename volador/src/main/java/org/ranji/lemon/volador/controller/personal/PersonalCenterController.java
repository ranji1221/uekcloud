package org.ranji.lemon.volador.controller.personal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonalCenterController {
	
	@Autowired
	private IPerService personalService;
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IUserInfoService userInfoService ;
	
	/*
	 * 个人中心 - 评论留言
	 * */
	@RequestMapping(value="/personalCenter_notice", method=RequestMethod.GET)
	public ModelAndView personalCenterNotice(){
		ModelAndView mv = new ModelAndView();
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
			List<Integer> listCourseId= personalService.findStudyingCourseRelationByUserId(userId);
			List<Course> courseList = new ArrayList<Course>() ;
			for(int i=0;i<listCourseId.size();i++){
				try {
					courseList.add(courseService.find(listCourseId.get(i)));
				}catch (NullPointerException e) {
					// TODO: handle exception
					break;
				}
			}
			mv.addObject(courseList);
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			mv.addObject("address",userInfo.getAddress());
			
			//查询学习时长
			
			
			//查询我的积分
			
			
			//查询签到天数
			mv.setViewName("/backend/wqf_learn_now");
		} catch (Exception e) {
			// TODO: handle exception
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
			List<Integer> listCourseId= personalService.findStudyedCourseRelationByUserId(userId);
			List<Course> courseList = new ArrayList<Course>() ;
			for(int i=0;i<listCourseId.size();i++){
				try {
					courseList.add(courseService.find(listCourseId.get(i)));
				}catch (NullPointerException e) {
					// TODO: handle exception
					break;
				}
			}
			mv.addObject("courseCount",listCourseId.size());
			mv.addObject(courseList);
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			mv.addObject("address",userInfo.getAddress());
			
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
	@RequestMapping(value="/personalCenter_fiavourite_course", method=RequestMethod.GET)
	public ModelAndView personalCenterFiavourite_course(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		
		try {
			int userId=(int) request.getSession().getAttribute("userId");
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			mv.addObject("address",userInfo.getAddress());
			mv.setViewName("/backend/wzq_fiavourite_end");
		} catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		//查询当前用户信息
		
		
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
			mv.setViewName("/backend/wzq_list_work_upload");
		} catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}

		return mv;
	}
	
	/*
	 * 个人中心 - 作业上传
	 * */
	@RequestMapping(value="/personalCenter_uploadHomework", method=RequestMethod.GET)
	public ModelAndView personalCenterUploadHomework(){
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
	
	
	

}
