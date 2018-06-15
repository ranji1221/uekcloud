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
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.ranji.lemon.core.util.DateUtil;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Carouse;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Direction;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.model.course.Theme;
import org.ranji.lemon.volador.model.global.Feedback;
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
import org.ranji.lemon.volador.service.global.prototype.IFeedbackService;
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
	@Autowired
	private IFeedbackService feedbackService;

	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
	public static String myEmailAccount = "uek@uekedu.com";
	public static String myEmailPassword = "ejTDZnuknJUYtpxW";

	// 首页
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		List<Map> notificationList = new ArrayList<>();
		int userId = 0;
		int notificationSize = 0;

		// 检测是否登录
		try {
			// 登录成功，返回userName及head_image
			String userName = (String) request.getSession().getAttribute("userName");
			if(null != request.getSession().getAttribute("userId")){
				userId = (int) request.getSession().getAttribute("userId");
				UserInfo userInfo = personalService.findUserInfoByUserId(userId);
				mv = headerService.headInfo(userId, userName);
				mv.addObject("head_image", userInfo.getHead_image());
			}
			
			mv.addObject("login_yes", "login_yes active");
			mv.addObject("login_no", "login_no");
			mv.addObject("userName", userName);

			// 查看用户是否已经签到
			// 获取用户签到表
			SignIn signIn = signInService.findSignInByUserId(userId);
			if(null != signIn){
				// 日期格式化
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

				// 格式化需要判断的日期
				String strNeedCheckDate = simpleDateFormat.format(signIn.getUpdateTime());

				// 格式化当前日期
				Date currentDate = new Date();
				String strCurrentDate = simpleDateFormat.format(currentDate);

				// 比较是否在同一天
				if (strNeedCheckDate.equals(strCurrentDate)) {
					// 如果是同一天说明用户已经签到
					mv.addObject("isSignIn", "done");
				} else {
					mv.addObject("isSignIn", "no");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("login_yes", "login_yes");
			mv.addObject("login_no", "login_no active");
			e.printStackTrace();
		}

		// 空指针异常处理，当未获取到数据时候，前台显示资料为空
		try {
			// 获取轮播图
			List<Carouse> carouseList = carouseService.findAll();
			mv.addObject("carouseList", carouseList);

			// 获取优秀学员
			List<Student> studentList = studentService.findAll();
			mv.addObject("studentList", studentList);

			// 获取首页课程方向及对应分类
			getCourseDirectionAndClassify(mv);

			// 获取首页推荐课程
			getIndexRecommendedCourse(mv);

			// 获取首页推荐老师
			getIndexRecommendedTeacher(mv);

			// 绑定用户未忽略的信息
			int ignoreNitificationNumber = 0;
			Map map = notificationService.findIgnoreNotificationByUser(userId);
			if (map != null && !map.isEmpty()) {
				ignoreNitificationNumber = Integer.parseInt(map.get("ignore_notification_number").toString());
			}
			int startIgnNotificationNumber = ignoreNitificationNumber + 1;
			int endIgnNotificationNumber = notificationService.maxNotificationId();
			notificationList = notificationService.findTop3Notification(startIgnNotificationNumber,
					endIgnNotificationNumber);
			notificationSize = notificationService.notReadNumber(startIgnNotificationNumber, endIgnNotificationNumber);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		mv.addObject("notificationList", notificationList);
		mv.addObject("notificationSize", notificationSize);
		mv.addObject("userId", userId);

		mv.setViewName("backend/index");
		return mv;
	}

	// 获取首页课程方向及对应分类
	public void getCourseDirectionAndClassify(ModelAndView mv) {
		List<Map> directionAndClassifyList = new ArrayList<Map>();

		// 首页显示课程方向
		List<Direction> directionList = directionService.findAll();

		// 定义首页显示的课程分类
		List<Classify> classifyList = new ArrayList<>();

		for (Direction direction : directionList) {
			Map<String, Object> directionAndClassifyMap = new HashMap<String, Object>();
			directionAndClassifyMap.put("direction", direction);
			directionAndClassifyMap.put("classifyList", directionService.findClassifyByDirectionId(direction.getId()));

			directionAndClassifyList.add(directionAndClassifyMap);
		}
		mv.addObject("directionAndClassifyList", directionAndClassifyList);
	}

	// 获取首页推荐课程
	public void getIndexRecommendedCourse(ModelAndView mv) {
		// 首页主题展示列表
		
		List<Map> themeAndCourseList = themeService.findCourseByThemeId(null);
		int i = 0;
		for(Map themeAndCourseMap:themeAndCourseList){
			Theme theme = (Theme) themeAndCourseMap.get("theme");
			i++;
			mv.addObject("themeCourse" + Integer.toString(i), themeAndCourseMap);
		}
	}

	// 获取课程及其对应分类
	public List<Map> getCourseAndClassify(List<Course> courseList) {
		List<Map> CourseAndClassifyList = new ArrayList<Map>();
		for (Course course : courseList) {
			HashMap<String, Object> courseMap = new HashMap<String, Object>();
			courseMap.put("courseId", course.getId());
			courseMap.put("courseName", course.getCourse_name());
			courseMap.put("course_price", course.getCourse_price());
			courseMap.put("student_count", course.getStudent_count());
			courseMap.put("image", course.getCourse_image_address());
			courseMap.put("classify", classifyService.findClassifyByCourseId(course.getId()).getClassify_name());
			CourseAndClassifyList.add(courseMap);
		}

		return CourseAndClassifyList;
	}

	// 获取首页推荐老师
	public void getIndexRecommendedTeacher(ModelAndView mv) {
		List<Teacher> teacherList = teacherService.findHeaderTeacher();
		mv.addObject(teacherList);
	}

	// 基本资料
	@RequestMapping(value = "/personal_basic", method = RequestMethod.GET)
	public ModelAndView personalBasicPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			String userName = request.getSession().getAttribute("userName").toString();
			int userId = (int) request.getSession().getAttribute("userId");
			mv = headerService.headInfo(userId, userName);
			// 获取用户
			Per person = personalService.findByUserName(userName);

			// 获取用户信息
			UserInfo userInfo = userInfoService
					.find(personalService.findUserUserInfoRelationByUserId(person.getId()).get(0));

			// 获取省市县，年月日的值
			String birthday = userInfo.getBirthday();
			if (null != birthday && !birthday.isEmpty()) {
				String[] birthdayArray = birthday.split("-");
				mv.addObject("year", birthdayArray[0]);
				mv.addObject("month", birthdayArray[1]);
				mv.addObject("day", birthdayArray[2]);
			}
			String address = userInfo.getAddress();
			if (null != address && !address.isEmpty()) {
				String[] addressArray = address.split("-");
				if(3 < addressArray.length ||3 == addressArray.length){
					mv.addObject("provincial", addressArray[0]);
					mv.addObject("municipal", addressArray[1]);
					mv.addObject("county", addressArray[2]);
				}
				
			}

			// 返回页面需要显示的用户信息
			mv.addObject("userName", userName);
			mv.addObject(userInfo);
		} catch (Exception e) {
			mv.addObject("userName", "游客");
			mv.setViewName("redirect:/login");
			return mv;
		}

		mv.setViewName("backend/wqf_personal_basic");
		return mv;
	}
	
	//反馈信息发送Ajax
	@RequestMapping(value="feedback.do",method=RequestMethod.POST)
	public void feedback(HttpServletRequest request,HttpServletResponse response) throws IOException{
		//设置返回格式
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		Map result=new HashMap();
		
		//获取参数
		String content=request.getParameter("message");
		Feedback feedback=new Feedback();
		feedback.setContent(content);
		
		feedbackService.save(feedback);
		result.put("code", 200);
		
		
		pw.print(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();
		
	}

	/**
	 * 用户基本信息设置
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/personal_basic", method = RequestMethod.POST)
	public void personalBasic(@RequestParam(value = "photo", required = false) MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置返回格式
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();

		Map<String, Object> map = new HashMap<String, Object>();
		// 保存用户与收藏课程关系
		try {
			// 接受请求参数
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

			if (null != username && !username.isEmpty()) {
				Per user = personalService.findByUserName(username);
				if (null != user) {

					UserInfo userinfo = personalService.findUserInfoByUserId(user.getId());

					// 如果用户上传文件
					if (!file.isEmpty()) {
						String head_image = saveFile(username, file);

						userinfo.setHead_image(head_image);
					}
					
					if(null != sex){
						userinfo.setGender(sex.equals("man") ? "男" : "女");
					}
					
					userinfo.setNickname(nickname);
					userinfo.setQq(qq);
					userinfo.setReal_name(realname);
					userinfo.setWechat(wechat);

					// 保存用户生日
					Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
					// 如果用户未输入，设置默认日期
					int year_int = year.isEmpty() ? 1990 : Integer.parseInt(year);
					int month_int = month.isEmpty() ? 1 : Integer.parseInt(month) - 1;
					int day_int = day.isEmpty() ? 1 : Integer.parseInt(day);
					cal.set(year_int, month_int, day_int);

					// 通过格式化输出日期
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					userinfo.setBirthday(format.format(cal.getTime()));

					// 保存用户地址信息
					userinfo.setAddress(provincial + "-" + municipal + "-" + county);

					// 更新用户信息
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

	// 文件保存到后台
	private String saveFile(String username, MultipartFile file) {
		String filePath = "";

		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 获取文件后缀名
				String fileName = file.getName();
				// 设置头像默认后缀
				String suffix = ".png";
				// 设置图像保存地址
				filePath = "/we/user/" + username + suffix;
				File saveDir = new File(
						"D:/volador_home/data/Img/"+ filePath);

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

	// 账号资料
	@RequestMapping(value = "/personal_set", method = RequestMethod.GET)
	public ModelAndView personalSetPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String userName = (String) request.getSession().getAttribute("userName");
		if(null != request.getSession().getAttribute("userId")){
			int userId = (int) request.getSession().getAttribute("userId");
			UserInfo userInfo = personalService.findUserInfoByUserId(userId);
			mv = headerService.headInfo(userId, userName);
			mv.addObject("head_image", userInfo.getHead_image());
		}
		
		mv.addObject("login_yes", "login_yes active");
		mv.addObject("login_no", "login_no");
		mv.addObject("userName", userName);

		mv.setViewName("backend/wqf_personal_set");
		return mv;
	}

	@RequestMapping(value = "/personal_set", method = RequestMethod.POST)
	public ModelAndView personalSet(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();

		String userName = request.getSession().getAttribute("userName").toString();

		// 获取用户
		// Per person = personalService.findByUserName(userName);

		mv.addObject("userName", userName);
		mv.setViewName("backend/wqf_personal_set");
		return mv;
	}


	// 清空消息
	@RequestMapping(value = "/clearNotification", method = RequestMethod.POST)
	public ModelAndView clearNotification(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int userId = Integer.parseInt(request.getParameter("userId"));
		// String userName =
		// request.getSession().getAttribute("userName").toString();
		// mv.addObject("userName", userName);
		int notificationNumber = notificationService.maxNotificationId();
		Map map = new HashMap();
		map.put("userId", userId);
		map.put("ignoreNotificationNumber", notificationNumber);
		Map existMap = notificationService.findIgnoreNotificationByUser(userId);
		if (existMap != null && !existMap.isEmpty()) {

			notificationService.updateIgnoreNotNum(map);
		} else {
			notificationService.saveUserAndIgnoreNotificationRelation(userId, notificationNumber);
		}

		mv.setViewName("redirect:/index");
		return mv;
	}

	// 修改密码
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public ModelAndView changePassword(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("backend/changePassword");
		return mv;
	}

	// 修改密码
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ModelAndView setPassword(@RequestParam(value = "password", required = false) String password,
			@RequestParam(value = "passwordOld", required = false) String passwordOld,
			@RequestParam(value = "passwordNew", required = false) String passwordNew, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			if (request.getSession().getAttribute("userId") != null) {
				int userId = (int) request.getSession().getAttribute("userId");
				// 获取用户
				Per user = personalService.find(userId);

				Subject subject = SecurityUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), password);
				// 如果用户能登录说明旧密码正确
				subject.login(token);

				// 新旧密码是否一致
				if (passwordOld.equals(passwordNew)) {
					// 检查密码是否合法
					String regEx = "^[^\\s]{6,20}$";
					Pattern pattern = Pattern.compile(regEx);
					Matcher matcher = pattern.matcher(passwordNew);
					boolean rs = matcher.matches();
					if (!rs) {
						mv.addObject("messege", "新旧密码不一致");
					} else {
						// 清除session
						HttpSession session = request.getSession();
						session.invalidate();
						subject.logout();
						user.setPassword(passwordNew);
						user.setUpdateTime(DateUtil.now());
						personalService.update(user);

						// 用户重新登录
						mv.setViewName("redirect:/login");
					}
				} else {
					mv.addObject("message", "两次输入新密码不一致");
					mv.setViewName("backend/changePassword");
				}
			} else {
				mv.setViewName("redirect:/login");
			}

		} catch (AuthenticationException e) {
			mv.addObject("message", "输入旧密码不正确");
			mv.setViewName("backend/changePassword");
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("redirect:/login");
		}

		return mv;
	}

	// 获取验证邮箱页面
	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public ModelAndView getEmail() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("backend/email");
		return mv;
	}

	// 获取验证邮箱页面
	@RequestMapping(value = "/email", method = RequestMethod.POST)
	public ModelAndView setEmail(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			String code = request.getParameter("code");
			String email = request.getParameter("ownEmail");
			int userId = (int) request.getSession().getAttribute("userId");
			UserInfo userInfo = personalService.findUserInfoByUserId(userId);

			//查看邮箱是否已经绑定
			if(null == userInfoService.findUserInfoByEmail(email)){
				if (!checkCode(request.getSession().getAttribute("code").toString(), code)) {
					// 验证短信验证码
					mv.addObject("message", "验证码错误!");
					mv.setViewName("backend/email");
				} else {
					// 保存用户设置的邮箱
					userInfo.setEmail(email);
					userInfoService.update(userInfo);
					mv.setViewName("backend/wqf_personal_set");
				}
			}else{
				mv.addObject("message", "邮箱已被绑定!");
				mv.setViewName("backend/email");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("backend/wqf_personal_set");
		}

		return mv;
	}

	/**
	 * 验证邮箱验证码是否正确
	 * 
	 * @param smsCheckCode
	 *            手动输入的邮箱验证码
	 * @param code
	 *            session中存放的邮箱验证码
	 * @return 验证码正确返回True
	 */
	public Boolean checkCode(String smsCheckCode, String code) {
		Boolean isValidCode = false;
		try {
			if (!smsCheckCode.isEmpty() && smsCheckCode.equals(code.toString())) {
				isValidCode = true;
			}
		} catch (Exception e) {
			throw new RuntimeException("短信验证失败", e);
		}
		return isValidCode;
	}

	// 获取邮箱验证码
	@RequestMapping(value = "/sendEmailCode", method = RequestMethod.POST)
	public void sendEmailCode(HttpServletRequest request, HttpServletResponse response) {
		String ownEmail = request.getParameter("ownEmail");
		String emailCode = request.getParameter("emailCode");

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp.exmail.qq.com");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.ssl.enable", "true");

		// 2. 根据配置创建会话对象, 用于和邮件服务器交互
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log

		try {

			// 3. 创建一封邮件
			MimeMessage message = createMimeMessage(session, myEmailAccount, ownEmail, emailCode);

			// 4. 根据 Session 获取邮件传输对象
			Transport transport = session.getTransport();
			// 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
			transport.connect(myEmailAccount, myEmailPassword);
			// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients()
			// 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
			transport.sendMessage(message, message.getAllRecipients());
			// 7. 关闭连接
			transport.close();

			/** 邮件验证码存入session */
			request.getSession().setAttribute("code", emailCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建一封只包含文本的简单邮件
	 * 
	 * @param session
	 *            和服务器交互的会话
	 * @param sendMail
	 *            发件人邮箱
	 * @param receiveMail
	 *            收件人邮箱
	 * @return
	 * @throws Exception
	 */
	public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String emailCode)
			throws Exception {
		// 1. 创建一封邮件
		MimeMessage message = new MimeMessage(session);

		// 2. From: 发件人
		message.setFrom(new InternetAddress(sendMail, "飞鱼平台", "UTF-8"));

		// 3. To: 收件人（可以增加多个收件人、抄送、密送）
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "飞鱼用户", "UTF-8"));

		// 4. Subject: 邮件主题
		message.setSubject("邮箱验证码", "UTF-8");

		// 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
		message.setContent("飞鱼用户您好, 您的邮箱验证码为" + emailCode, "text/html;charset=UTF-8");

		// 6. 设置发件时间
		message.setSentDate(new Date());

		// 7. 保存设置
		message.saveChanges();

		return message;
	}
}
