package org.ranji.lemon.volador.controller.growthclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.model.growthclass.GrowthClass;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
import org.ranji.lemon.volador.model.personal.Integral;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.course.prototype.ITeacherService;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthClassService;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthStageService;
import org.ranji.lemon.volador.service.personal.prototype.IIntegralService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.ISignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrowthSystemController {
	@Autowired
	private IPerService personalService;
	@Autowired
	private IGrowthClassService growthClassService;
	@Autowired
	private IGrowthStageService growthStageService;
	@Autowired
	private ISignInService signInService;
	@Autowired
	private ITeacherService teacherService;
	@Autowired
	private IIntegralService integralService;
	
	@RequestMapping(value="/growth_system", method=RequestMethod.GET)
	public ModelAndView getGrowthSystmePage(
			@RequestParam(value="growthClassId", required=false) Integer growthClassId,
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try{
			//根据session获取userId，查询正在学习课程
			if(null != request.getSession().getAttribute("userId")){
				int userId=(int) request.getSession().getAttribute("userId");
				
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
			}
			
			//获取职业导航
			List<GrowthClass> growthClassList = growthClassService.findAll();
			mv.addObject("growthClassList", growthClassList);
			
			if(null == growthClassId){
				growthClassId = growthClassList.get(0).getId();
			}
			
			//获取职业导航			
			mv.addObject("growthClass", growthClassService.find(growthClassId));
			List<GrowthStage> growthStageList= growthClassService.findGrowthStageByGrowthClassId(growthClassId);
			//添加成長階段
			//獲取階段時長及學習人數
			int timeCount = 0;
			int studentCount = 0;
			int coursePrice = 0;
			for(GrowthStage growthStage:growthStageList){
				//返回成長體系總課程時長，課程價格，學生人數
				timeCount += growthStage.getTimeCount();
				studentCount += growthStage.getStudentCount();
				coursePrice += growthStage.getCoursePrice();
				mv.addObject("growthStage"+String.valueOf(growthStage.getNumber()), growthStage);
			}
			
			
			//返回成長體系總課程時長，課程價格，學生人數
			mv.addObject("timeCount", timeCount);
			mv.addObject("studentCount", studentCount);
			mv.addObject("coursePrice", coursePrice);
			
			//根据导航ID查找对应的老师
			mv.addObject("teacherList", teacherService.findTeacherByGrowthClassId(growthClassId));
			mv.addObject("growthStageList", growthStageList);
			
		}catch(NullPointerException e){
			e.printStackTrace();
		}
				
		mv.setViewName("/backend/wqf_occupation");
		return mv;
	}
	
	@RequestMapping(value="/growth_system", method=RequestMethod.POST)
	public ModelAndView growthSystmePage(
			@RequestParam(value="growthClassId", required=false) Integer growthClassId,
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try{
			//根据session获取userId，查询正在学习课程
			if(null != request.getSession().getAttribute("userId")){
				int userId=(int) request.getSession().getAttribute("userId");
				
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
			}
			
			//获取职业导航			
			List<Map> growthStageMapList = new ArrayList<Map>();
			HashMap<String, Object> params = new HashMap<String, Object>();
			//职业导航類型
			mv.addObject("growthClass", growthClassService.find(growthClassId));
			//params
			List<GrowthStage> growthStageList= growthClassService.findGrowthStageByGrowthClassId(growthClassId);
			//添加成長階段
			params.put("growthStageList", growthStageList);
			//獲取階段時長及學習人數
			int timeCount = 0;
			int studentCount = 0;
			int coursePrice = 0;
			for(GrowthStage growthStage:growthStageList){
				timeCount += growthStage.getTimeCount();
				studentCount += growthStage.getStudentCount();
				coursePrice += growthStage.getCoursePrice();
			}
			params.put("timeCount", timeCount);
			params.put("studentCount", studentCount);
			params.put("coursePrice", coursePrice);
			
			growthStageMapList.add(params);
			
			mv.addObject("growthClassAndStageList", growthStageMapList);
			
		}catch(NullPointerException e){
			e.printStackTrace();
		}
				
		mv.setViewName("/backend/wqf_occupation");
		return mv;
	}
	
}
