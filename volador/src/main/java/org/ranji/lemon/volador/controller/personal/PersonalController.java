package org.ranji.lemon.volador.controller.personal;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Theme;
import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.course.prototype.IThemeService;
import org.ranji.lemon.volador.service.global.prototype.INotificationService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.ISignInService;
import org.ranji.lemon.volador.service.personal.prototype.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonalController {
	@Autowired
	private IPerService personalService;
	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private IThemeService themeService;
	@Autowired
	private ICourseService courseService;
	@Autowired
	private IClassifyService classifyService;
	@Autowired
	private INotificationService notificationService;
	@Autowired
	private ISignInService signInService;
	
	//首页
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView indexPage(HttpServletRequest request ){
		ModelAndView mv = new ModelAndView();
		List<Map> notificationList = new ArrayList<>();
		int userId = 0;
		//检测是否登录
		try{
			//登录成功，返回userName及head_image
			String userName=(String) request.getSession().getAttribute("userName");
			userId=(int) request.getSession().getAttribute("userId");
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("head_image",userInfo.getHead_image());
			mv.addObject("login_yes","login_yes active");
			mv.addObject("login_no","login_no");
			mv.addObject("userName", userName);
			
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
				mv.addObject("isSignIn", "done");
			}else{
				mv.addObject("isSignIn", "no");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			mv.addObject("login_yes","login_yes");
			mv.addObject("login_no","login_no active");
		}
		
		Map <String, Object> paramCourse= new HashMap<String, Object>();
		List<Course> classifyCourseList = new ArrayList<>();
		List<Theme> themeList = themeService.findAll();
		Map <String, Object> params= new HashMap<String, Object>();
		
		//空指针异常处理，当未获取到数据时候，前台显示资料为空
		try {
			//获取首页动态显示课程列表
			List<Classify> classifyList = classifyService.findAll();
			for(Classify classify:classifyList){
				classifyCourseList = classifyService.findCourseByClassify(classify.getId());
				paramCourse.put("Classify"+Integer.toString(classify.getId()), classifyCourseList);
			}
			
			//首页显示课程分类
			//查找课程分类对应的课程
			for (Theme theme:themeList){
				//返回课程分类
				params.put("theme" + Integer.toString(theme.getId()), theme);
				//返回分类下的课程
				List<Course> courseList = themeService.findCourseAndThemeRelationByCourseId(theme.getId());
				params.put("themeCourse" + Integer.toString(theme.getId()), courseList);
				
			}
			
			//绑定用户未忽略的信息
			
			int ignoreNitificationNumber = 0;
			Map map = notificationService.findIgnoreNotificationByUser(userId);
			if(map!=null&&!map.isEmpty()){
				ignoreNitificationNumber = Integer.parseInt(map.get("ignore_notification_number").toString());
			}
			int startIgnNotificationNumber = ignoreNitificationNumber+1;
			int endIgnNotificationNumber = notificationService.getTotalOfItems();
			notificationList = notificationService.findTop3Notification(startIgnNotificationNumber, endIgnNotificationNumber);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		mv.addAllObjects(paramCourse);
		mv.addAllObjects(params);	
		mv.addObject("notificationList", notificationList);
		mv.addObject("notificationSize", notificationList.size());

		mv.setViewName("/backend/index");
		return mv;
	}
	
	//基本资料
	@RequestMapping(value="/personal_basic", method=RequestMethod.GET)
	public ModelAndView personalBasicPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try{
			String userName = request.getSession().getAttribute("userName").toString();
			//获取用户
			Per person = personalService.findByUserName(userName);
			
			//获取用户信息
			UserInfo userInfo = userInfoService.find(personalService.findUserUserInfoRelationByUserId(person.getId()).get(0));
			
			//返回页面需要显示的用户信息
			mv.addObject("userName", userName);
			mv.addObject(userInfo);
		}
		catch (Exception e) {
			mv.addObject("userName", "游客");
			mv.addObject(new UserInfo());
			mv.setViewName("redirect:/login");
			return mv;
		}		
		
		mv.setViewName("/backend/wqf_personal_basic");
		return mv;
	}
	@RequestMapping(value="/personal_basic", method=RequestMethod.POST)
	public ModelAndView personalBasic(
			@RequestParam(value="photo",required=false) MultipartFile file,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="realname",required=false) String realname,
			@RequestParam(value="nickname",required=false) String nickname,
			@RequestParam(value="sex",required=false) String sex,
			@RequestParam(value="qq",required=false) String qq,
			@RequestParam(value="wechat",required=false) String wechat,
			@RequestParam(value="year",required=false) String year,
			@RequestParam(value="place",required=false) String place,
			HttpServletRequest request){
				
		ModelAndView mv = new ModelAndView();
		if (null != username && !username.isEmpty()){
			Per user = personalService.findByUserName(username);
			if(null != user){

				UserInfo userinfo = personalService.findUserInfoByUserId(user.getId());
				
				//如果用户上传文件
				if(!file.getName().equals("")){
					String head_image = saveFile(username, file);
					userinfo.setHead_image(head_image);
				}
				
				userinfo.setGender(sex.equals("man")?"男":"女");
				userinfo.setNickname(nickname);
				userinfo.setQQ(qq);
				userinfo.setReal_name(realname);
				userinfo.setWechat(wechat);
				
				//更新用户信息
				userInfoService.update(userinfo);
				
				mv.addObject(userinfo);			
			}
			mv.setViewName("/backend/wqf_personal_basic");
		}
		else{
			mv.setViewName("redirect:/login");
		}
	
		
		return mv;
	}

	//文件保存到后台
	private String saveFile(String username,
            MultipartFile file) {
		String filePath = "";
		
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
            	//获取文件后缀名
            	String fileName = file.getName();
        		//String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            	String suffix = ".png";
        		/*
        		//如果用户未登录或session过期则给默认头像
                if(null == username || ("").equals(username)){
                	username = "wqf_user";
                	suffix = ".png";
                }
                */
        		
            	filePath = "photos\\" + username+ suffix;
               
                File saveDir = new File("E:\\JAVA_WORKSPACE\\UNIQUE\\uekcloud\\volador\\src\\main\\resources\\static\\" + filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();

                // 转存文件
                file.transferTo(saveDir);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }
	
	//账号资料
	@RequestMapping(value="/personal_set", method=RequestMethod.GET)
	public ModelAndView personalSetPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();

		String userName = request.getSession().getAttribute("userName").toString();
		mv.addObject("userName", userName);
		mv.setViewName("/backend/wqf_personal_set");
		return mv;
	}
	@RequestMapping(value="/personal_set", method=RequestMethod.POST)
	public ModelAndView personalSet(
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();	

		String userName = request.getSession().getAttribute("userName").toString();
		
		//获取用户
		//Per person = personalService.findByUserName(userName);
				
		mv.addObject("userName", userName);
		mv.setViewName("/backend/wqf_personal_set");
		return mv;
	}
	
	//点击课程，跳转到章节页面
	@RequestMapping(value="course", method=RequestMethod.GET)
	public ModelAndView coursePage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/course/chapter");
		return mv;
	}

}
