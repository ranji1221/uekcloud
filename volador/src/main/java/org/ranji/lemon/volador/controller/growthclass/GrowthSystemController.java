package org.ranji.lemon.volador.controller.growthclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.StudyingCourse;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.model.growthclass.GrowthClass;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
import org.ranji.lemon.volador.model.growthclass.LabelClassify;
import org.ranji.lemon.volador.model.growthclass.StageLabel;
import org.ranji.lemon.volador.model.personal.Integral;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.course.prototype.ITeacherService;
import org.ranji.lemon.volador.service.global.prototype.INotificationService;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthClassService;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthStageService;
import org.ranji.lemon.volador.service.growthclass.prototype.ILabelClassifyService;
import org.ranji.lemon.volador.service.growthclass.prototype.IStageLabelService;
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
	@Autowired
	private IStageLabelService stageLabelService;
	@Autowired
	private ILabelClassifyService labelClassifyService;
	@Autowired
	private   INotificationService notificationService;
	
	@RequestMapping(value="/growth_system", method=RequestMethod.GET)
	public ModelAndView getGrowthSystmePage(
			@RequestParam(value="growthClassId", required=false) Integer growthClassId,
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try{
			//获取全都职业导航
			List<GrowthClass> growthClassList = growthClassService.findAll();
			if(null == growthClassId){
				growthClassId = growthClassList.get(0).getId();
			}
			
			//获取用户查看的职业导航			
			List<GrowthStage> growthStageList= growthClassService.findGrowthStageByGrowthClassId(growthClassId);
			
			mv = getStageAndLebalList(growthStageList);
			mv.addObject("growthClassList", growthClassList);
			mv.addObject("growthClass", growthClassService.find(growthClassId));
			
			
			//根据导航ID查找对应的老师
			mv.addObject("teacherList", teacherService.findTeacherByGrowthClassId(growthClassId));
			mv.addObject("growthStageList", growthStageList);
			
			//获取头部
			getHeader(mv, request);
			
		}catch(NullPointerException e){
			e.printStackTrace();
		}
				
		mv.setViewName("/backend/wqf_occupation");
		return mv;
	}
	
	//获取职业导航阶段及对应阶段图标及类别
	public ModelAndView getStageAndLebalList(List<GrowthStage> growthStageList){
		List<Map> stageAndLebalList = new ArrayList<Map>();
		ModelAndView mv = new ModelAndView();
		//添加成長階段
		//獲取階段時長及學習人數
		int timeCount = 0;
		int studentCount = 0;
		int coursePrice = 0;
		
		for(GrowthStage growthStage:growthStageList){
			
			Map<String, Object> stageAndLebalMap = new HashMap<String, Object>();
			stageAndLebalMap.put("stage", growthStage);
			
			//获取该阶段下绑定的阶段图标描述
			List<StageLabel> stageLabelList = stageLabelService.findStageLabelByStageId(growthStage.getId());
			List<Map> labelAndClassifyList = new ArrayList<Map>();
			for(StageLabel stageLabel:stageLabelList){
				Map<String, Object> labelAndClassifyMap = new HashMap<String, Object>();
				labelAndClassifyMap.put("stageLabel", stageLabel);
				//获取阶段图标下对应的分类
				labelAndClassifyMap.put("labelClassifyList", labelClassifyService.findLabelClassifyByLabelId(stageLabel.getId()));
				//将数据添加到列表中
				labelAndClassifyList.add(labelAndClassifyMap);
			}
			stageAndLebalMap.put("labelAndClassifyList", labelAndClassifyList);
			//统计成長體系總課程時長，課程價格，學生人數
			timeCount += growthStage.getTimeCount();
			studentCount += growthStage.getStudentCount();
			coursePrice += growthStage.getCoursePrice();
			
			stageAndLebalList.add(stageAndLebalMap);
		}
		//返回成長體系總課程時長，課程價格，學生人數
		mv.addObject("stageAndLebalList", stageAndLebalList);
		mv.addObject("timeCount", timeCount);
		mv.addObject("studentCount", studentCount);
		mv.addObject("coursePrice", coursePrice);
		return mv;
	}
	
	public void getHeader(ModelAndView mv, HttpServletRequest request){
		//根据session获取userId
		int notificationSize = 0;
		List<Map> notificationList = new ArrayList<>();
		try{
			if(null != request.getSession().getAttribute("userId")){
				int userId=(int) request.getSession().getAttribute("userId");
				String userName=(String) request.getSession().getAttribute("userName");
				//登录成功，返回userName及head_image
				UserInfo userInfo=personalService.findUserInfoByUserId(userId);
				mv.addObject("head_image",userInfo.getHead_image());
				mv.addObject("headLogin_yes","login_yes active");
				mv.addObject("headLogin_no","login_no");
				mv.addObject("headUserName", userName);	
				
				//绑定用户未忽略的信息
				
				int ignoreNitificationNumber = 0;
				Map map = notificationService.findIgnoreNotificationByUser(userId);
				if(map!=null&&!map.isEmpty()){
					ignoreNitificationNumber = Integer.parseInt(map.get("ignore_notification_number").toString());
				}
				int startIgnNotificationNumber = ignoreNitificationNumber+1;
				int endIgnNotificationNumber = notificationService.maxNotificationId();
				notificationList = notificationService.findTop3Notification(startIgnNotificationNumber, endIgnNotificationNumber);
				notificationSize = notificationService.notReadNumber(startIgnNotificationNumber, endIgnNotificationNumber);
				mv.addObject("headnotificationList", notificationList);
				mv.addObject("headnotificationSize", notificationSize);
				mv.addObject("userId", userId);
			}else{
				mv.addObject("headLogin_yes","login_yes");
				mv.addObject("headLogin_no","login_no active");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	//用户收藏职业导航
	@RequestMapping(value="/collectGrowthSystem", method=RequestMethod.POST)
	public void collectGrowthSystme(
			@RequestParam(value="growthId", required=false) Integer growthId,
			@RequestParam(value="userId", required=false) Integer userId,
			HttpServletRequest request){
		try{
			growthClassService.saveGrowthClassOfChapterId(userId, growthId, null, null);
			
		}catch(NullPointerException e){
			e.printStackTrace();
		}
	}
	
}
