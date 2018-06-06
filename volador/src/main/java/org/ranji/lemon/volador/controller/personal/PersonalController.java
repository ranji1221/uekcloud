package org.ranji.lemon.volador.controller.personal;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Carouse;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Direction;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.model.course.Theme;
import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.model.personal.Student;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.course.prototype.ICarouseService;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.course.prototype.IDirectionService;
import org.ranji.lemon.volador.service.course.prototype.ITeacherService;
import org.ranji.lemon.volador.service.course.prototype.IThemeService;
import org.ranji.lemon.volador.service.global.prototype.INotificationService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.ISignInService;
import org.ranji.lemon.volador.service.personal.prototype.IStudentService;
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
	@Autowired
	private IDirectionService directionService;
	@Autowired
	private IheaderService headerService;
	@Autowired
	private ITeacherService teacherService;
	@Autowired
	private ICarouseService carouseService;
	@Autowired
	private IStudentService studentService;
	//首页
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView indexPage(HttpServletRequest request ){
		ModelAndView mv = new ModelAndView();
		List<Map> notificationList = new ArrayList<>();
		int userId = 0;
		int notificationSize = 0;
		
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
			e.printStackTrace();
		}
		
		//空指针异常处理，当未获取到数据时候，前台显示资料为空
		try {
			//获取轮播图
			List<Carouse> carouseList = carouseService.findAll();
			mv.addObject("carouseList", carouseList);
			
			//或许优秀学员
			List<Student> studentList = studentService.findAll();
			mv.addObject("studentList", studentList);
			 
			//获取首页课程方向及对应分类
			getCourseDirectionAndClassify(mv);
			
			//获取首页推荐课程
			getIndexRecommendedCourse(mv);
			
			//获取首页推荐老师
			getIndexRecommendedTeacher(mv);
			
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
			e.printStackTrace();
		}
					
		mv.addObject("notificationList", notificationList);
		mv.addObject("notificationSize", notificationSize);
		mv.addObject("userId", userId);

		mv.setViewName("/backend/index");
		return mv;
	}


	
	//获取首页课程方向及对应分类
	public void getCourseDirectionAndClassify(ModelAndView mv){
		List<Map> directionAndClassifyList = new ArrayList<Map>();
		
		//首页显示课程方向
		List<Direction> directionList = directionService.findAll();
		
		//定义首页显示的课程分类
		List<Classify> classifyList = new ArrayList<>();

		for(Direction direction:directionList){
			Map <String, Object> directionAndClassifyMap= new HashMap<String, Object>();
			directionAndClassifyMap.put("direction", direction);
			directionAndClassifyMap.put("classifyList", directionService.findClassifyByDirectionId(direction.getId()));

			directionAndClassifyList.add(directionAndClassifyMap);
		}
		mv.addObject("directionAndClassifyList", directionAndClassifyList);
	}
	
	//获取首页推荐课程
	public void getIndexRecommendedCourse(ModelAndView mv){
		//首页主题展示列表
		List<Theme> themeList = themeService.findAll();
		List<Map> themeCourseList = new ArrayList<Map>();
		for (Theme theme:themeList){
			HashMap<String, Object> params = new HashMap<String, Object>();
			
			//返回课程分类
			params.put("theme", theme);
			//返回分类下的课程
			List<Course> courseList = themeService.findCourseAndThemeRelationByCourseId(theme.getId());
			params.put("courses", getCourseAndClassify(courseList));
			
			themeCourseList.add(params);
			mv.addObject("themeCourse"+Integer.toString(theme.getId()), params);
		}
		mv.addObject("themeCourseList", themeCourseList);
	}
	
	//获取课程及其对应分类
	public List<Map> getCourseAndClassify(List<Course> courseList){
		List<Map> CourseAndClassifyList = new ArrayList<Map>();
		for(Course course:courseList){
			HashMap<String, Object> courseMap= new HashMap<String, Object>();
			courseMap.put("courseId", course.getId());
			courseMap.put("courseName", course.getCourse_name());
			courseMap.put("course_price", course.getCourse_price());
			courseMap.put("student_count", course.getStudent_count());
			courseMap.put("image", course.getCourse_image_address());
			courseMap.put("classify", classifyService.findClassifyByCourseId(course.getId()).getClassify_name());
			System.out.println(courseMap.toString());
			System.out.println("-----------------");
			CourseAndClassifyList.add(courseMap);
		}
		
		return CourseAndClassifyList;
	}
	
	//获取首页推荐老师
	public void getIndexRecommendedTeacher(ModelAndView mv){
		List<Teacher> teacherList = teacherService.findHeaderTeacher();
		mv.addObject(teacherList);
	}
	
	//基本资料
	@RequestMapping(value="/personal_basic", method=RequestMethod.GET)
	public ModelAndView personalBasicPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try{
			String userName = request.getSession().getAttribute("userName").toString();
			int userId=(int) request.getSession().getAttribute("userId");
			mv =headerService.headInfo(userId, userName);
			//获取用户
			Per person = personalService.findByUserName(userName);
			
			//获取用户信息
			UserInfo userInfo = userInfoService.find(personalService.findUserUserInfoRelationByUserId(person.getId()).get(0));
			
			//获取省市县，年月日的值
			String birthday = userInfo.getBirthday();
			if(null != birthday && !birthday.isEmpty()){
				String[] birthdayArray = birthday.split("-");
				mv.addObject("year", birthdayArray[0]);
				mv.addObject("month", birthdayArray[1]);
				mv.addObject("day", birthdayArray[2]);
			}
			String address = userInfo.getAddress();
			if(null != address && !address.isEmpty()){
				String[] addressArray = address.split("-");
				mv.addObject("provincial", addressArray[0]);
				mv.addObject("municipal", addressArray[1]);
				mv.addObject("county", addressArray[2]);
			}
			
			//返回页面需要显示的用户信息
			mv.addObject("userName", userName);
			mv.addObject(userInfo);
		}
		catch (Exception e) {
			mv.addObject("userName", "游客");
			mv.setViewName("redirect:/login");
			return mv;
		}		
		
		mv.setViewName("/backend/wqf_personal_basic");
		return mv;
	}
	
	/**
	 * 用户基本信息设置
	 * @param file
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/personal_basic", method=RequestMethod.POST)
	public void personalBasic(
			@RequestParam(value="photo",required=false) MultipartFile file,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		//设置返回格式
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		
		
		Map<String,Object> map=new HashMap<String,Object>();
		//保存用户与收藏课程关系
		try {
			//接受请求参数
			String username = request.getParameter("username");
			String realname = request.getParameter("realname");
			String nickname = request.getParameter("nickname");
			String sex = request.getParameter("sex");
			String qq = request.getParameter("qq");
			String wechat = request.getParameter("wechat");
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String provincial = request.getParameter("provincial");
			String municipal = request.getParameter("municipal");
			String county = request.getParameter("county");

			
			if (null != username && !username.isEmpty()){
				Per user = personalService.findByUserName(username);
				if(null != user){

					UserInfo userinfo = personalService.findUserInfoByUserId(user.getId());
					
					//如果用户上传文件
					if(!file.isEmpty()){
						String head_image = saveFile(username, file);

						userinfo.setHead_image(head_image);
					}
					
					userinfo.setGender(sex.equals("man")?"男":"女");
					userinfo.setNickname(nickname);
					userinfo.setQq(qq);
					userinfo.setReal_name(realname);
					userinfo.setWechat(wechat);
					
					//保存用户生日
					Calendar cal = Calendar.getInstance();//使用默认时区和语言环境获得一个日历。
					//如果用户未输入，设置默认日期
					int year_int = year.isEmpty()?1990:Integer.parseInt(year);
					int month_int = month.isEmpty()?1:Integer.parseInt(month) - 1;
					int day_int = day.isEmpty()?1:Integer.parseInt(day);
					cal.set(year_int,month_int,day_int);
					
					//通过格式化输出日期    
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					userinfo.setBirthday(format.format(cal.getTime()));
					
					//保存用户地址信息
					userinfo.setAddress(provincial+"-"+municipal+"-"+county);
					
					//更新用户信息
					userInfoService.update(userinfo);	
					map.put("result", 0);			
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			map.put("result", 1);
		}	
		
		writer.print(JsonUtil.toJsonByProperties(map)); 
        writer.flush();  
        writer.close();

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
                //设置头像默认后缀
            	String suffix = ".png";
        		//设置图像保存地址
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

	//清空消息
	@RequestMapping(value="/clearNotification", method=RequestMethod.POST)
	public ModelAndView clearNotification(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
        int userId = Integer.parseInt(request.getParameter("userId"));
		//String userName = request.getSession().getAttribute("userName").toString();
		//mv.addObject("userName", userName);
        int notificationNumber = notificationService.getTotalOfItems();
        Map map = new HashMap();
        map.put("userId", userId);
        map.put("ignoreNotificationNumber", notificationNumber);
        Map existMap = notificationService.findIgnoreNotificationByUser(userId);
        if(existMap!=null&&!existMap.isEmpty()){
        	
            notificationService.updateIgnoreNotNum(map);
        }else{
        	notificationService.saveUserAndIgnoreNotificationRelation(userId, notificationNumber);
        }
       
        mv.setViewName("redirect:/index");
		return mv;
	}
	
	//修改密码
	@RequestMapping(value="/changePassword", method=RequestMethod.GET)
	public ModelAndView changePassword(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/changePassword");
		return mv;
	}
	//修改密码
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	public ModelAndView setPassword(
			@RequestParam(value="password",required=false) String password,
			@RequestParam(value="passwordOld",required=false) String passwordOld,
			@RequestParam(value="passwordNew",required=false) String passwordNew,
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try{
			if(request.getSession().getAttribute("userId")!=null){
				int userId = (int) request.getSession().getAttribute("userId");
				//获取用户原密码
				
				Per user = personalService.find(userId);
				Subject subject = SecurityUtils.getSubject();						
				UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), password);
				//如果用户能登录说明旧密码正确
				subject.login(token);
				
				//新旧密码是否一致
				if(passwordOld.equals(passwordNew)){
					//检查密码是否合法
					String regEx = "^[^\\s]{6,20}$";
					Pattern pattern = Pattern.compile(regEx);
					Matcher matcher = pattern.matcher(passwordNew);
					boolean rs = matcher.matches();
					if(!rs){
						mv.addObject("messege", "新旧密码不一致");
					}else{
						user.setPassword(passwordNew);
						personalService.save(user);
					}
				}
			}
			else{
				mv.setViewName("redirect:/login");
			}
							
		}catch(Exception e){
			e.printStackTrace();
		}
		
		mv.setViewName("redirect:/personal_set");
		return mv;
	}
	
	//获取验证邮箱页面
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ModelAndView getEmail(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/email");
		return mv;
	}
	
	//获取验证邮箱页面
	@RequestMapping(value="/email", method=RequestMethod.POST)
	public ModelAndView setEmail(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/email");
		return mv;
	}
}
