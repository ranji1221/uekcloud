package org.ranji.lemon.volador.controller.personal;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Homework;
import org.ranji.lemon.volador.model.course.StudyingCourse;
import org.ranji.lemon.volador.model.global.Notification;
import org.ranji.lemon.volador.model.growthclass.GrowthClass;
import org.ranji.lemon.volador.model.personal.Integral;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.course.prototype.IChapterService;
import org.ranji.lemon.volador.service.course.prototype.IChapterTitleService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.course.prototype.IHomeworkService;
import org.ranji.lemon.volador.service.course.prototype.INoteService;
import org.ranji.lemon.volador.service.global.prototype.INotificationService;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthClassService;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthStageService;
import org.ranji.lemon.volador.service.pay.prototype.IOrderService;
import org.ranji.lemon.volador.service.personal.prototype.IIntegralService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.ISignInService;
import org.ranji.lemon.volador.service.personal.prototype.IUserInfoService;
import org.ranji.lemon.volador.service.personal.prototype.IheaderService;
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
	private IChapterTitleService chapterTitleService;
	
	@Autowired
	private IUserInfoService userInfoService ;
	
	@Autowired
	private INoteService noteService;
	
	@Autowired
	private IChapterService chapterService;
	
	@Autowired
	private IHomeworkService homeworkService ;
	
	@Autowired
	private ISignInService signInService;
	
	@Autowired
	private IIntegralService integralService;
	
	@Autowired
	private INotificationService inotificationService;
	
	@Autowired
	private IheaderService headerService;
	
	@Autowired
	private IGrowthClassService growthClassService;
	
	@Autowired
	private IGrowthStageService growthStageService;
	
	@Autowired
	private IOrderService orderService;
	
	//用户签到获得的积分
	private Integer SIGNIN_INTRGRAL_NUM = 20;
	
	/*
	 * 个人中心 - 通知公告
	 * */
	@RequestMapping(value="/personalCenter_notice", method=RequestMethod.GET)
	public ModelAndView personalCenterNotice(HttpServletRequest request){
		ModelAndView mv= new ModelAndView();
		try {
			String userName=(String) request.getSession().getAttribute("userName");
			int userId=(int) request.getSession().getAttribute("userId");
			mv = headerService.headInfo(userId, userName);
			 mv.addObject("pageUri", "/personalCenter_notice");
			List<Notification> notificationList = inotificationService.findAll();
			mv.addObject("notificationList", notificationList);
			mv.addObject("notificationNumber", notificationList.size());
			//System.out.println(notificationList.get(0).getCreateTime());	    
			
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
			
			mv.setViewName("backend/wqf_notice");
			
		} catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		return mv;
		
		
	}
	
	/*
	 * 个人中心 - 评论 
	 * */
	@RequestMapping(value="/personalCenter_comment", method=RequestMethod.GET)
	public ModelAndView personalCenterComment(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try {
			int userId=(int) request.getSession().getAttribute("userId");
			String userName=(String) request.getSession().getAttribute("userName");
			mv =headerService.headInfo(userId, userName);
			mv.addObject("pageUri", "/personalCenter_comment");
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		mv.setViewName("backend/wqf_comment");
		return mv;
	}
	
	
	/*
	 * 个人中心 - 正在学习的课程 
	 * */
	@RequestMapping(value="/personalCenter_learning", method=RequestMethod.GET)
	public ModelAndView personalCenterLearn_now(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		//异常处理，当没有获取到登录信息时，跳转到登录页面
		if(request.getSession().getAttribute("userId")!=null) {
			//根据session获取userId，查询正在学习课程
			int userId=(int) request.getSession().getAttribute("userId");
			String userName=(String) request.getSession().getAttribute("userName");
			mv =headerService.headInfo(userId, userName);
			mv.addObject("pageUri", "/personalCenter_learning");
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
			
			List<StudyingCourse> studyingCoursesList=new ArrayList<>();
			
			for(int i=0;i<courseList.size();i++){
				//获取最新学习时间及获取章节id
				StudyingCourse studyingCourse=personalService.findStudyingCourse(userId,courseList.get(i).getId());
				
				//课程名字
				studyingCourse.setCourseName(courseList.get(i).getCourse_name());
				//课程图片
				if(courseList.get(i).getCourse_image_address()==null){
					studyingCourse.setCourseImage("images/wzq_coursea_item_img.jpg");
				}else{
					studyingCourse.setCourseImage(courseList.get(i).getCourse_image_address());
				}
				//获取章节名称
				try {
					studyingCourse.setChapterName(chapterService.find(studyingCourse.getChapterId()).getChapter_name());
				} catch (Exception e) {
					// TODO: handle exception
					studyingCourse.setChapterName("你要看的章节消失啦");
					personalService.deleteStudyingCourseRelation(userId, courseList.get(i).getId());
				}

				//获取评论总数
				studyingCourse.setCommentCount(chapterService.findCommentListByChapter(studyingCourse.getChapterId()).size());
				//获取笔记总数
				studyingCourse.setNoteCount(noteService.findNoteByUserId(userId,studyingCourse.getChapterId()).size());
				//获取更新到哪一章
				try {
					List<ChapterTitle> chapterTitleList=courseService.findChapterTitleByCourse(courseList.get(i).getId());
					String updateChapter="更新至"+chapterTitleList.size()+"-";
					List<Chapter> chapterList=chapterTitleService.findChapterByChapterTitle(chapterTitleList.get((chapterTitleList.size()-1)).getId());
					updateChapter+=chapterList.size();
					studyingCourse.setUpdateChapter(updateChapter);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				studyingCoursesList.add(studyingCourse);	
			}
			
			mv.addObject("studyingCoursesList",studyingCoursesList);
			mv.addObject(courseList);
			mv.addObject("TotalNumber",pageCourse.getTotal());
			mv.addObject("currentPage",page);
			
			//计算返回页码
			int pageCount;
			if((pageCourse.getTotal()%3==0)){
				pageCount=pageCourse.getTotal()/3;
			}else{
				pageCount=(pageCourse.getTotal()/3)+1;
			}
			mv.addObject("pageCount",pageCount);
			mv.addObject("interfaceName","personalCenter_learning");
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			
			//查询学习时长
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
			
			mv.setViewName("backend/wqf_learn_now");
		} else{
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
		if(request.getSession().getAttribute("userId")!=null) {
			
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
			
		} else{
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
		if(request.getSession().getAttribute("userId")!=null) {
			//根据session获取userId，查询已学习，并查询当前用户信息
			int userId=(int) request.getSession().getAttribute("userId");
			String userName=(String) request.getSession().getAttribute("userName");
			mv =headerService.headInfo(userId, userName);
			mv.addObject("pageUri", "/personalCenter_learn_end");
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
			List<Map> studiedCourseList=new ArrayList<>();
			for(Course course:courseList){
				Map<String,Object> courseMap=new HashMap<String,Object>();
				//课程名称
				courseMap.put("courseName", course.getCourse_name());
				//课程图片
				if(course.getCourse_image_address()==null){
					courseMap.put("courseImg", "images/wzq_work_img.jpg");
				}else{
					courseMap.put("courseImg", course.getCourse_image_address());
				}
				//课程id
				courseMap.put("courseId", course.getId());
				//课程评论数
				courseMap.put("commentCount", courseService.findCommentListByCourse(course.getId()).size());
				//该课程笔记数
				List<ChapterTitle> chapterTitleList=courseService.findChapterTitleByCourse(course.getId());
				int noteCount = 0;
				try {
					for(ChapterTitle chapterTitle:chapterTitleList){
						List<Chapter> chapterList=chapterTitleService.findChapterByChapterTitle(chapterTitle.getId());
						for(Chapter chapter:chapterList){
							int i=noteService.findNoteByUserId(userId, chapter.getId()).size();
							noteCount=noteCount+i;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}

				courseMap.put("noteCount", noteCount);
				//更新至
				try {
					String updateChapter="更新至"+chapterTitleList.size()+"-";
					List<Chapter> chapterList=chapterTitleService.findChapterByChapterTitle(chapterTitleList.get((chapterTitleList.size()-1)).getId());
					updateChapter+=chapterList.size();
					courseMap.put("updateChapter", updateChapter);
				} catch (Exception e) {
					// TODO: handle exception
					courseMap.put("updateChapter", "暂无章节");
				}
				studiedCourseList.add(courseMap);
			}
			
			mv.addObject("studiedCourseList",studiedCourseList);
			
			int pageCount;
			if((pageCourse.getTotal()%6==0)){
				pageCount=pageCourse.getTotal()/6;
			}else{
				pageCount=(pageCourse.getTotal()/6)+1;
			}
			mv.addObject("pageCount",pageCount);
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			mv.addObject("interfaceName","personalCenter_learn_end");
			
			//查询学习时长
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
			
			
			mv.setViewName("backend/wqf_learn_end");
		} else{
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	
	/*
	 * 个人中心 - 删除某个已学习完的课程 
	 * */
	@RequestMapping(value="/delete_endcourse", method=RequestMethod.GET)
	public ModelAndView deleteEndCourse(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		//异常处理，当没有获取到登录信息时，跳转到登录页面
		if(request.getSession().getAttribute("userId")!=null) {
			//根据session获取userId，查询已学习，并查询当前用户信息
			int userId=(int) request.getSession().getAttribute("userId");

			int courseId=Integer.parseInt(request.getParameter("courseId"));

			//删除完成课程
			personalService.deleteStudyedCourseRelation(userId, courseId);
			//重定向到当前页
			mv.setViewName("redirect:/personalCenter_learn_end");
			
			}else {
				// TODO: handle exception
				mv.setViewName("redirect:/personalCenter_learn_end");
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
		if(request.getSession().getAttribute("userId")!=null) {
			int userId=(int) request.getSession().getAttribute("userId");
			String userName=(String) request.getSession().getAttribute("userName");
			mv =headerService.headInfo(userId, userName);
			mv.addObject("pageUri", "/personalCenter_collect_course");
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
			List<StudyingCourse> studyingCoursesList=new ArrayList<>();
			
			for(int i=0;i<courseList.size();i++){
				//获取最新学习时间及获取章节id
				StudyingCourse studyingCourse=new StudyingCourse();
				try {
					studyingCourse=personalService.findStudyingCourse(userId,courseList.get(i).getId());
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				//课程名字
				studyingCourse.setCourseName(courseList.get(i).getCourse_name());
				//课程图片
				if(courseList.get(i).getCourse_image_address()==null){
					studyingCourse.setCourseImage("images/wzq_coursea_item_img.jpg");
				}else{
					studyingCourse.setCourseImage(courseList.get(i).getCourse_image_address());
				}
				//获得课程ID
				studyingCourse.setCourseId(courseList.get(i).getId());
				
				try {
					//获取章节名称
					studyingCourse.setChapterName(chapterService.find(studyingCourse.getChapterId()).getChapter_name());
					//获取评论总数
					studyingCourse.setCommentCount(chapterService.findCommentListByChapter(studyingCourse.getChapterId()).size());
				} catch (Exception e) {
					// TODO: handle exception
					studyingCourse.setChapterName("：未开始学习");
					List<ChapterTitle> chapterTitleList=courseService.findChapterTitleByCourse(courseList.get(i).getId());
					studyingCourse.setChapterId(chapterTitleService.findChapterByChapterTitle(chapterTitleList.get(0).getId()).get(0).getId());
				}
				
				//获取笔记总数
				studyingCourse.setNoteCount(noteService.findNoteByUserId(userId,studyingCourse.getChapterId()).size());
				//获取更新到哪一章
				try {
					List<ChapterTitle> chapterTitleList=courseService.findChapterTitleByCourse(courseList.get(i).getId());
					String updateChapter="更新至"+chapterTitleList.size()+"-";
					List<Chapter> chapterList=chapterTitleService.findChapterByChapterTitle(chapterTitleList.get((chapterTitleList.size()-1)).getId());
					updateChapter+=chapterList.size();
					studyingCourse.setUpdateChapter(updateChapter);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				studyingCoursesList.add(studyingCourse);	
			}
			
			mv.addObject("studyingCoursesList",studyingCoursesList);
			
			
			mv.addObject(courseList);
			mv.addObject("TotalNumber",pageCourse.getTotal());
			mv.addObject("currentPage",page);
			
			int pageCount;
			if((pageCourse.getTotal()%3==0)){
				pageCount=pageCourse.getTotal()/3;
			}else{
				pageCount=(pageCourse.getTotal()/3)+1;
			}
			mv.addObject("pageCount",pageCount);
			
			//查询当前用户信息，并传递页面需要的用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			mv.setViewName("backend/wzq_fiavourite_end");
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
		} else{
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	/*
	 * 个人中心 - 删除某个收藏课程 
	 * */
	@RequestMapping(value="/delete_collect_course", method=RequestMethod.GET)
	public ModelAndView deleteCollectCourse(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		
		//异常处理，当没有获取到登录信息时候，跳转到登录页面
		if(request.getSession().getAttribute("userId")!=null)  {
			int userId=(int) request.getSession().getAttribute("userId");
			String userName=(String) request.getSession().getAttribute("userName");
			
			int courseId=Integer.parseInt(request.getParameter("courseId"));
			
			personalService.deleteCollectCourseRelation(userId, courseId);
			
			mv.setViewName("redirect:/personalCenter_collect_course");
			
			}else {
				// TODO: handle exception
				mv.setViewName("redirect:/personalCenter_collect_course");
			}
		return mv;
	}
	
	/*
	 * 个人中心 - 资料下载 
	 * */
	@RequestMapping(value="/personalCenter_data_download", method=RequestMethod.GET)
	public ModelAndView personalCenterData_download(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		if(request.getSession().getAttribute("userId")!=null) {
			//获取userId
			int userId=(int) request.getSession().getAttribute("userId");
			String userName=(String) request.getSession().getAttribute("userName");
			mv =headerService.headInfo(userId, userName);
			mv.addObject("pageUri", "/personalCenter_data_download");
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
			
			mv.setViewName("backend/wqf_data_download");
			
		} else {
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
		
		if(request.getSession().getAttribute("userId")!=null) {
			int userId=(int) request.getSession().getAttribute("userId");
			String userName=(String) request.getSession().getAttribute("userName");
			 mv = headerService.headInfo(userId, userName);	
			 mv.addObject("pageUri", "/personalCenter_jifen");
			 
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
			 
			mv.setViewName("backend/wzq_jifen");
		} else {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		
		return mv;
	}
	
	/*
	 * 个人中心 - 积分抽奖规则 
	 * */
	@RequestMapping(value="/personalCenter_draw_rule", method=RequestMethod.GET)
	public ModelAndView personalCenterDraw_rule(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		if(request.getSession().getAttribute("userId")!=null) {
			String userName = request.getSession().getAttribute("userName").toString();
			int userId=(int) request.getSession().getAttribute("userId");
			mv =headerService.headInfo(userId, userName);
			mv.addObject("pageUri", "/personalCenter_draw_rule");
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
			mv.setViewName("backend/wqf_draw_rule");
		} else {
			mv.setViewName("redirect:/login");
		}
		
		return mv;
	}
	
	/*
	 * 个人中心 - 我的作业
	 * */
	@RequestMapping(value="/personalCenter_homework", method=RequestMethod.GET)
	public ModelAndView personalCenterHomework(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		if(request.getSession().getAttribute("userId")!=null) {
			int userId=(int) request.getSession().getAttribute("userId");
			String userName=(String) request.getSession().getAttribute("userName");
			mv =headerService.headInfo(userId, userName);
			mv.addObject("pageUri", "/personalCenter_homework");
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
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
			
			mv.setViewName("backend/wzq_list_work_upload");
		} else {
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
			String userName=(String) request.getSession().getAttribute("userName");
			mv =headerService.headInfo(userId, userName);
			mv.addObject("pageUri", "/personalCenter_uploadHomework");
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			
			
			
			mv.setViewName("backend/wqf_upload_work");
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
		mv.setViewName("backend/wqf_upload_work");
		return mv;
	}
	
	/*
	 * 个人中心 - 作业展示
	 * */
	@RequestMapping(value="/personalCenter_showHomework", method=RequestMethod.GET)
	public ModelAndView personalCenterShowHomework(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("backend/wzq_show_work");
		return mv;
	}
	
	
	/*
	 * 个人中心 - 邀请码
	 * */
	@RequestMapping(value="/personalCenter_invitation", method=RequestMethod.GET)
	public ModelAndView personalCenterInvitation(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		if(request.getSession().getAttribute("userId")!=null){
			String userName=(String) request.getSession().getAttribute("userName");
			int userId=(int) request.getSession().getAttribute("userId");
			mv =headerService.headInfo(userId, userName);
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
			
			mv.addObject("pageUri", "/personalCenter_invitation");
			mv.setViewName("backend/wqf_invitation");
		} else{
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		
		return mv;
	}
	
	
	
	/*
	 * 个人中心 - 清空里面查询全部通知/公告
	 * */
	@RequestMapping(value="/personalCenter_allnotice", method=RequestMethod.GET)
	public ModelAndView personalAllCenterNotice(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try {
			String userName=(String) request.getSession().getAttribute("userName");
			 int userId=(int) request.getSession().getAttribute("userId");
			 int notificationNumber = inotificationService.maxNotificationId();	
			  Map map = new HashMap();
	          map.put("userId", userId);
	          map.put("ignoreNotificationNumber", notificationNumber);
	          Map existMap = inotificationService.findIgnoreNotificationByUser(userId);
	          if(existMap!=null&&!existMap.isEmpty()){
	          	
	        	  inotificationService.updateIgnoreNotNum(map);
	          }else{
	        	  inotificationService.saveUserAndIgnoreNotificationRelation(userId, notificationNumber);
	          }
			mv.setViewName("redirect:/personalCenter_notice");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return mv;
	}
	
	//用户获取个人中心职业导航
	@RequestMapping(value="/myGrowthSystem", method=RequestMethod.GET)
	public ModelAndView getMyGrowthSystem(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		//异常处理，当没有获取到登录信息时，跳转到登录页面
		try {
			//根据session获取userId，查询正在学习课程
			int userId=(int) request.getSession().getAttribute("userId");
			String userName=(String) request.getSession().getAttribute("userName");
			mv =headerService.headInfo(userId, userName);
			mv.addObject("pageUri", "/personalCenter_learning");
			
			List<Map> growthClassAndStageList = growthClassService.findUserCollectGrowth(userId);
			System.out.println(growthClassAndStageList.toString());
			mv.addObject("growthClassAndStageList", growthClassAndStageList);
			//获得职业导航数目
			mv.addObject("count", growthClassAndStageList.size());
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("address", addressArray[1]);
				}
			}
			
			
			//查询学习时长
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
			
			mv.setViewName("backend/wzq_zhiye");
		} catch (Exception e) {
			// TODO: handle exception
			mv.setViewName("redirect:/login");
		}
		return mv;
	}
	
	//使用CDKey
	@RequestMapping(value="/useCDKey",method=RequestMethod.POST)
	public void useCDkey(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		
		int userId=(int) request.getSession().getAttribute("userId");
		String CDKey=request.getParameter("CDKey");
		
		Map<String,Object> result=orderService.useCDKey(userId,CDKey);
		
		pw.print(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();
		
	}

}
