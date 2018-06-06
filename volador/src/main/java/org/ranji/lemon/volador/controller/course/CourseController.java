package org.ranji.lemon.volador.controller.course;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.assertj.core.util.DateUtil;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Comment;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Direction;
import org.ranji.lemon.volador.model.course.Note;
import org.ranji.lemon.volador.model.course.Reply;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.course.prototype.IChapterService;
import org.ranji.lemon.volador.service.course.prototype.IChapterTitleService;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.ICommentService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.course.prototype.IDirectionService;
import org.ranji.lemon.volador.service.course.prototype.INoteService;
import org.ranji.lemon.volador.service.course.prototype.IReplyService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.IheaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IChapterService chapterService;
	
	@Autowired
	private IClassifyService classifyService;
	
	@Autowired
	private IReplyService replyService;
	
	@Autowired
	private IPerService personalService;
	
	@Autowired
	private IChapterTitleService chapterTitleService;
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private INoteService noteService;
	
	@Autowired
	private IDirectionService directionService;
	
	@Autowired
	private IheaderService headerService;
	//职业导航
	@RequestMapping(value="/professionalNavigation", method=RequestMethod.GET)
	public ModelAndView professionalNavigationPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wzq_zhiye");
		return mv;
	}
	
	//课程频道
	@RequestMapping(value="/courseChannel", method=RequestMethod.GET)
	public ModelAndView courseChannelPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/cp_courseChannel");
		return mv;
	}
	
	//找课程
	@RequestMapping(value="/findCourse", method=RequestMethod.GET)
	public ModelAndView findCoursePage(
			@RequestParam(value="directionId", required=false) Integer directionId,
			@RequestParam(value="classfyId", required=false) Integer classfyId,
			@RequestParam(value="price", required=false) String strPrice,
			HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		try{
			
			if(request.getSession().getAttribute("userId")!=null
					&&request.getSession().getAttribute("userName")!=null){
				int userId=(int) request.getSession().getAttribute("userId");
				String userName=(String) request.getSession().getAttribute("userName");
				mv =headerService.headInfo(userId, userName);
				mv.addObject("pageUri", "/findCourse");
			}else{
				mv.addObject("headLogin_yes","login_yes");
				mv.addObject("headLogin_no","login_no active");
			}
			
		
			//界面显示所有课程方向
			List<Direction> directionList = directionService.findAll();		
			mv.addObject(directionList);
			request.getSession().setAttribute("directionId", directionId);
			
			List<Classify> classfyList = new ArrayList<>();
			
			//保存用户选择的课程方向
			request.getSession().setAttribute("classfyId", classfyId);
			
			List<Course> courseList = new ArrayList<>();
			//查询全部课程
			if(null == directionId && null == classfyId && null == strPrice){
				courseList = courseService.findAll();
			}
			else if(null != directionId && null == classfyId && null == strPrice){
				courseList = directionService.findCourseByDirectionId(directionId);
			}
			else if(null != classfyId && null == strPrice){
				courseList = classifyService.findCourseByClassify(classfyId);
				//获取用户所选分类的课程方向
				directionId = directionService.findDirectionIdByClassiyId(classfyId);
				request.getSession().setAttribute("directionId", directionId);
			}else if(null != strPrice){
				double price = Double.valueOf(strPrice);
				courseList = courseService.findCourseByPrice(price);								
			}
			//保存用户选择价格
			request.getSession().setAttribute("price", strPrice);
			mv.addObject(courseList);
			
			//界面显示课程分类
			if(null == directionId){
				classfyList = classifyService.findAll();
			}
			else{
				classfyList = directionService.findClassifyByDirectionId(directionId);
			}
			mv.addObject("classfyList",classfyList);
			
			//保存方向及分类选择
			if(null != directionId){
				mv.addObject("option", "directionId="+String.valueOf(directionId));
			}else if(null != classfyId){
				mv.addObject("option", "classfyId="+String.valueOf(classfyId));
			}			
			
			mv.setViewName("/backend/wqf_find");
		} catch (Exception e){
			e.printStackTrace();
			mv.setViewName("/backend/index");
		}												
		return mv;
	}
	
	//课程章节
	@RequestMapping(value="/course_chapter", method=RequestMethod.GET)
	public ModelAndView chapterPage(@RequestParam(value="courseId", required=false) Integer courseId,
			HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		
		//检测是否登录,并获取用户信息
		try{		
			String userName = request.getSession().getAttribute("userName").toString();
			int userId=(int) request.getSession().getAttribute("userId");
			mv =headerService.headInfo(userId, userName);
			mv.addObject("pageUri", "/course_chapter?courseId="+courseId);
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("login_yes","login_yes active");
			mv.addObject("login_no","login_no");
			mv.addObject("userName", userName);
			mv.addObject("userId",userId);
			//查询是否已经收藏该课程
			List<Integer> courseIdList=personalService.findCollectCourseRelationByUserId(userId);
			int display=0;
			for(int courseNum:courseIdList){
				if(courseNum == courseId){
					display=1;
				};
			}
			mv.addObject("display", display);
			
		}
		catch (Exception e) {
			mv.addObject("login_yes","login_yes");
			mv.addObject("login_no","login_no active");
			mv.addObject("display", 0);
		}
		
		if(null != courseId){
			//查询课程
			Course course=courseService.find(courseId);
			
			//如果查询到的courseId为空，重定向到404页面
			if(course == null){
				mv.setViewName("redirect:/notFound");
				return mv;
			}
			
			if(course.getCourse_image_address()==null){
				course.setCourse_image_address("images/cp_10.png");
			}
			//将课程信息返回
			if(course.getCourse_price()!=0){
				try {
					int userId=(int) request.getSession().getAttribute("userId");
					List<Integer> courseIdList= personalService.findBuyCourseRelationByUserId(userId);
					for(int buyCourseId:courseIdList){
						if (buyCourseId==courseId){
							course.setCourse_price(-1);
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			mv.addObject(course);
			
			//查找课程对应教师
			try {
				Teacher teacher=courseService.findTeacherbyCourse(courseId).get(0);
				//返回教师信息
				mv.addObject(teacher);
			} catch (Exception e) {
				// TODO: handle exception
				Teacher teacher=new Teacher();
				mv.addObject(teacher);
			}
			
			
			
			
			//根据课程id查询章节标题集合
			List<ChapterTitle> chapterTitleList=courseService.findChapterTitleByCourse(courseId);
			HashMap<String,Object> chapterListMap=new HashMap<String,Object>();
			for(int i=0;i<chapterTitleList.size();i++){
				//根据章节标题id查询章节
				chapterListMap.put("chapter"+i,chapterTitleService.findChapterByChapterTitle(chapterTitleList.get(i).getId()));
			}
			//返回给前台
			String chapterJson=JsonUtil.objectToJson(chapterListMap);
			try {
				mv.addObject("goStudy",chapterTitleService.findChapterByChapterTitle(chapterTitleList.get(0).getId()).get(0).getId());
			} catch (Exception e) {
				// TODO: handle exception
				
			}

			mv.addObject("courseId", courseId);
			mv.addObject("count",chapterTitleList.size());
			mv.addAllObjects(chapterListMap);
			mv.addObject("chapterJson",chapterJson);
			mv.addObject(chapterTitleList);
			
			mv.setViewName("/backend/wqf_chapter");
		}
		else{
			//重定向到跳转到404
			mv.setViewName("redirect:/index");
//			mv.addObject("error", "operate error!");
		}
												
		return mv;
	}
	
	//课程评论页面
	@RequestMapping(value="/course_comment", method=RequestMethod.GET)
	public ModelAndView commentPage(@RequestParam(value="courseId", required=false) Integer courseId,
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		//检测是否登录,并获取用户信息
		try{
			String userName = request.getSession().getAttribute("userName").toString();
			int userId=(int) request.getSession().getAttribute("userId");
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("userId", userId);
			mv.addObject("login_yes","login_yes active");
			mv.addObject("login_no","login_no");
			mv.addObject("userName", userName);
		}
		catch (Exception e) {
			UserInfo userInfo=new UserInfo();
			mv.addObject(userInfo);
			mv.addObject("login_yes","login_yes");
			mv.addObject("login_no","login_no active");
		}
		if(null != courseId){
			//查询课程
			Course course=courseService.find(courseId);
			
			//如果查询到的courseId为空，重定向到404页面
			if(course == null){
				mv.setViewName("redirect:/notFound");
				return mv;
			}
			
			if(course.getCourse_image_address()==null){
				course.setCourse_image_address("images/cp_10.png");
			}
			//将课程信息返回
			mv.addObject(course);
			
			//查找课程对应教师
			Teacher teacher=courseService.findTeacherbyCourse(courseId).get(0);
			
			//返回教师信息
			mv.addObject(teacher);
			
			
			
			//根据课程id获取评论列表
			List<Comment> commentList = courseService.findCommentListByCourse(courseId);
			//根据评论获取用户信息
			for(int i=0;i<commentList.size();i++){
				int commentId=commentList.get(i).getId();
				int user_Id=commentService.findUserIdByCommentId(commentId).get(0);
				UserInfo userInfo=personalService.findUserInfoByUserId(user_Id);
				commentList.get(i).setNickName(userInfo.getNickname());
				commentList.get(i).setHead_image(userInfo.getHead_image());
			}
			
			mv.addObject("commentCount",commentList.size());
			mv.addObject(commentList);
			mv.setViewName("/backend/cp_comment");
		}else{
			mv.setViewName("redirect:/index");
		}
		
		
		
		return mv;
	}
	
	//收藏课程Ajax
	@RequestMapping(value="/course_collect",method=RequestMethod.POST)
	public void collectCourse(
				HttpServletRequest request,
				HttpServletResponse response) throws IOException{
			//设置返回格式
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			
			
			
			Map<String,Object> map=new HashMap<String,Object>();
			//保存用户与收藏课程关系
			try {
				//接受请求参数
				int userId=Integer.parseInt(request.getParameter("userId"));
				int courseId=Integer.parseInt(request.getParameter("courseId"));
				personalService.saveUserAndCollectCourseRelation(userId, courseId);
				map.put("result", 200);
			} catch (Exception e) {
				// TODO: handle exception
				map.put("result", 400);
			}	
			
			writer.print(JsonUtil.toJsonByProperties(map)); 
	        writer.flush();  
	        writer.close();

		}
	
	//取消收藏课程Ajax
	@RequestMapping(value="/course_uncollect",method=RequestMethod.POST)
	public void uncollectCourse(
				HttpServletRequest request,
				HttpServletResponse response) throws IOException{
			//设置返回格式
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();

			//接受请求参数
			int userId=Integer.parseInt(request.getParameter("userId"));
			int courseId=Integer.parseInt(request.getParameter("courseId"));
			
			
			Map<String,Object> map=new HashMap<String,Object>();
			//删除用户与收藏课程关系
			try {
				personalService.deleteCollectCourseRelation(userId, courseId);
				map.put("result", 200);
			} catch (Exception e) {
				// TODO: handle exception
				map.put("result", 400);
			}			
			
			writer.print(JsonUtil.toJsonByProperties(map)); 
	        writer.flush();  
	        writer.close();

		}
	
	
	
	//课程发表评论AJAX
	@RequestMapping(value="/course_addcomment",method=RequestMethod.POST)
	public void courseComment(@RequestParam(value="content", required=false) String content,
				@RequestParam(value="courseId", required=false) String courseId,
				@RequestParam(value="userId", required=false) String userId,
				HttpServletRequest request,
				HttpServletResponse response) throws IOException{
			//设置返回格式
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			String info="用户未登录，请登录！";
			Map<String,Object> map=new HashMap<String,Object>();
			
			//判断是否登录
			if(!userId.equals("")){
				try {					
					Comment comment=new Comment();
					int courseid=Integer.parseInt(request.getParameter("courseId"));
					
					//保存评论
					comment.setContent(content);
					commentService.save(comment);
					int commentId=comment.getId();
					//保存评论和课程的关系
					commentService.saveCourseAndCommentRelation(commentId, Integer.parseInt(courseId.toString()));
					
					//保存评论和用户关系
					commentService.savaCommentAndUserRelation(comment.getId(), Integer.parseInt(userId.toString()));
					
					info = "success";
					//返回刚刚评论内容
					map.put("headImage", personalService.findUserInfoByUserId(Integer.parseInt(userId)).getHead_image());
					map.put("content", content);
					map.put("nickName", personalService.findUserInfoByUserId(Integer.parseInt(userId)).getNickname());
//					map.put("data", data);
					map.put("info", info);
					
					map.put("info", info);
				}catch (Exception e) {
					info = "异常！";
					map.put("info",info);
					e.printStackTrace();
				}
			}else{
				map.put("info", "未登录");
			}
			
			
			writer.print(JsonUtil.toJsonByProperties(map)); 
	        writer.flush();  
	        writer.close();

		}

	//作业展示页面
	@RequestMapping(value="/course_jobDisplay", method=RequestMethod.GET)
	public ModelAndView jobDisplayPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/cp_jobDisplay");
		return mv;
	}
	
	//视频播放页面
	@RequestMapping(value="/course_video", method=RequestMethod.GET)
	public ModelAndView videoPage(@RequestParam(value="chapterId", required=false) Integer chapterId,
			HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		if(chapterId == null){
			//重定向到404页面
			mv.setViewName("redirect:/index");
			return mv;
		}
		
		//查询章节信息
		Chapter chapter=chapterService.find(chapterId);
		//如果查到的内容为空，重定向到404页面
		if(chapter==null){
			mv.setViewName("redirect:/notFound");
			return mv;
		}
		//根据章节id查找课程标题id,查找课程标题id查找课程id
		int courseId=chapterTitleService.find(chapter.getChapter_title_id()).getCourse_id();
		
		try {
			//判断用户是否登录，获取用户登录信息
			int userId=(int) request.getSession().getAttribute("userId");
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("userId", userId);
			mv.addObject("login_yes","login_yes active");
			mv.addObject("login_no","login_no");
			personalService.deleteStudyingCourseRelation(userId, courseId);
			
			List<ChapterTitle> chapterTitleList=courseService.findChapterTitleByCourse(courseId);
			List<Chapter> chapterList=chapterTitleService.findChapterByChapterTitle(chapterTitleList.get((chapterTitleList.size()-1)).getId());
			if(chapterId == chapterList.get(chapterList.size()-1).getId()){
				personalService.saveUserAndStudyedCourseRelation(userId, courseId);
			}else
				{
					personalService.saveUserAndStudyingCourseRelation(userId, courseId, new Date(), chapterId);
				}
		} catch (Exception e) {
			// TODO: handle exception
			UserInfo userInfo=new UserInfo();
			userInfo.setHead_image("images/wzq_user_img.jpg");
			mv.addObject(userInfo);
			mv.addObject("login_yes","login_yes");
			mv.addObject("login_no","login_no active");
		}
		

		//判断此章节视频是否可以播放
		boolean power=false;
		if(courseService.find(courseId).getCourse_price() !=0){
			//判断是否为付费课程第一章第一节
			if(chapterTitleService.findChapterByChapterTitle(courseService.findChapterTitleByCourse(courseId).get(0).getId()).get(0).getId()==chapterId){
				power=true;
			}else{
				try {
					int userId=(int) request.getSession().getAttribute("userId");
					List<Integer> courseIdList= personalService.findBuyCourseRelationByUserId(userId);
					for(int buyCourseId:courseIdList){
						if (buyCourseId==courseId){
							power=true;
						}
					}
				} catch (Exception e) {
					// TODO: handle exception
					
				}
			}
		}else{
			power=true;
		}
		
		if(power){
			mv.addObject(chapter);
			mv.addObject("courseId",courseId);
			mv.setViewName("/backend/cp_video");
		}else{
			mv.setViewName("redirect:/course_chapter?courseId="+courseId);
		}
		
		return mv;
	}
	
	//获取章节信息AJax
	@RequestMapping(value="/chapterList",method=RequestMethod.POST)
	public void chapList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//设置返回格式
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		//获取请求参数
		int courseId =Integer.parseInt(request.getParameter("courseId"));
		
		//根据课程id查询章节标题集合
		List<ChapterTitle> chapterTitleList=courseService.findChapterTitleByCourse(courseId);
		HashMap<String,Object> chapterListMap=new HashMap<String,Object>();
		for(int i=0;i<chapterTitleList.size();i++){
			//根据章节标题id查询章节
			chapterListMap.put("chapter"+i,chapterTitleService.findChapterByChapterTitle(chapterTitleList.get(i).getId()));
		}
		
		String chapterJson=JsonUtil.objectToJson(chapterListMap);
		
		//拼接为Json返回
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("chapterTitle", chapterTitleList);
		map.put("chapterList", chapterListMap);

		pw.write(JsonUtil.objectToJson(map));
		pw.flush();
		pw.close();
	}
	
	
	//获取章节评论Ajax
	@RequestMapping(value="/chapter_commentList",method=RequestMethod.POST)
	public void chapterCommentList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//设置返回格式
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		//获取请求参数
		int chapterId =Integer.parseInt(request.getParameter("chapterId"));
		String headImage=null;
		
		if(request.getParameter("userId").equals("")){
			headImage="images/wzq_user_img.jpg";
		}else{
			//获取用户头像
			int userId=Integer.parseInt(request.getParameter("userId"));
			headImage= personalService.findUserInfoByUserId(userId).getHead_image();
		}
		
		
		
		//根据章节id获取评论列表
		List<Comment> commentList = chapterService.findCommentListByChapter(chapterId);
		
		//根据评论获取用户信息
		for(int i=0;i<commentList.size();i++){
			int commentId=commentList.get(i).getId();
			int user_Id=commentService.findUserIdByCommentId(commentId).get(0);
			UserInfo userInfo=personalService.findUserInfoByUserId(user_Id);
			commentList.get(i).setNickName(userInfo.getNickname());
			commentList.get(i).setHead_image(userInfo.getHead_image());
		}
		
		//拼接为Json返回
		Map<String,Object> map=new HashMap<String,Object>();
		System.out.println(commentList.toString());
		map.put("UserHeadImage", headImage);
		map.put("commentList", commentList);
		
		pw.write(JsonUtil.objectToJson(map));
		pw.flush();
		pw.close();
	}
	
	//获取章节评论与回复Ajax
	@RequestMapping(value="/chapterCommentList",method=RequestMethod.POST)
	public void chapterCommentAndReply(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		//设置返回格式
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		Map<String,Object> result=new HashMap<String,Object>();
		try {
			//获取请求参数
			int chapterId =Integer.parseInt(request.getParameter("chapterId"));
			
			//获取当前用户信息
			if(request.getParameter("userId").equals("")){
				UserInfo userInfo=new UserInfo();
				userInfo.setHead_image("images/wzq_user_img.jpg");
				result.put("userInfo", userInfo);
			}else{
				//获取用户头像
				int userId=Integer.parseInt(request.getParameter("userId"));
				UserInfo userInfo= personalService.findUserInfoByUserId(userId);
				result.put("userInfo", userInfo);
			}
			
			//根据章节id获取评论列表
			List<Comment> commentList = chapterService.findCommentListByChapter(chapterId);
			
			List<Map> commentAndReplyList=new ArrayList<>();
			
			for(Comment comment:commentList){
				//根据评论获取用户信息
				Map<String,Object> commentAndReplyMap=new HashMap<String,Object>();
				int commentId=comment.getId();
				int user_Id=commentService.findUserIdByCommentId(commentId).get(0);
				UserInfo userInfo=personalService.findUserInfoByUserId(user_Id);
				comment.setNickName(userInfo.getNickname());
				comment.setHead_image(userInfo.getHead_image());
				comment.setUserId(userInfo.getId());
				commentAndReplyMap.put("comment", comment);
				
				//获取回复信息
				List<Reply> replyList=replyService.findReplyByCommentId(commentId);
				
				for(Reply reply:replyList){
					if(reply.getUserId()!=0){
						reply.setUserName(personalService.findUserInfoByUserId(reply.getUserId()).getNickname());
					}
					if(reply.getReplyUserId() !=0){
						reply.setReplyUserName(personalService.findUserInfoByUserId(reply.getReplyUserId()).getNickname());
					}
				}
				
				commentAndReplyMap.put("reply", replyList);
				commentAndReplyList.add(commentAndReplyMap);
			}
			
			//拼接为Json返回		
			result.put("code", 200);
			result.put("message", "获取成功");
			result.put("commentAndReply", commentAndReplyList);
			
		} catch (Exception e) {
			// TODO: handle exception
			result.put("code", 404);
			result.put("message", "获取失败");
		}
		
		pw.write(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();
	}
	
	
	
	//章节发表评论Ajax
	@RequestMapping(value="/course_chapter_comment",method=RequestMethod.POST)
	public void chapterComment(@RequestParam(value="content", required=false) String content,
			@RequestParam(value="chapterId", required=false) String chapterId,
			@RequestParam(value="userId", required=false) String userId,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();
		String info;
		Map<String,Object> map=new HashMap<String,Object>();
		
		if(!userId.equals("")){
				try {			
				System.out.println(userId);
				
				Comment comment=new Comment();
				
				//保存评论
				comment.setContent(content);
				commentService.save(comment);
				
				//保存评论和章节的关系
				commentService.saveCommentAndChapterRelation(comment.getId(), Integer.parseInt(chapterId.toString()));
				
				//保存评论和用户关系
				commentService.savaCommentAndUserRelation(comment.getId(), Integer.parseInt(userId.toString()));
				
				info = "success";
				map.put("info", info);
			  } catch (Exception e) {
				info = "fail";
				map.put("info",info);
				e.printStackTrace();
			}
		}else{
			info="未登录";
			map.put("info", info);
		}
		
		writer.print(JsonUtil.objectToJson(map)); 
        writer.flush();  
        writer.close();
	}
	
	//章节评论下回复Ajax
	@RequestMapping(value="/chapter_comment_reply",method=RequestMethod.POST)
	public void chapterReply(
			HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter writer = response.getWriter();

		Map<String,Object> result=new HashMap<String,Object>();
		
		try {
			Reply replyIn=new Reply();
			int commentId=Integer.parseInt(request.getParameter("commentId"));
			int userId=Integer.parseInt(request.getParameter("userId"));
			int replyUserId=Integer.parseInt(request.getParameter("replyUserId"));
			String reply=request.getParameter("reply");
			
			replyIn.setCommentId(commentId);
			replyIn.setReply(reply);
			replyIn.setReplyUserId(replyUserId);
			replyIn.setUserId(userId);
			
			//保存回复信息
			replyService.save(replyIn);
			
			result.put("code", 200);
			result.put("message", "请求成功");
		} catch (Exception e) {
			// TODO: handle exception
			result.put("code", 404);
			result.put("message", "请求失败");
		}
		
		
		
		
		writer.print(JsonUtil.objectToJson(result)); 
        writer.flush();  
        writer.close();
	}
	
	
	
	//视频笔记页面
	@RequestMapping(value="/course_chapterNote", method=RequestMethod.GET)
	public ModelAndView videoWorkPage(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		try{
			int chapterId=Integer.parseInt(request.getParameter("chapterId"));
			int userId=(int) request.getSession().getAttribute("userId");
			mv.addObject("chapterId",chapterId);
			mv.addObject("userId", userId);
			
			//查询用户所有用户笔记
			List<Note> noteList = noteService.findNoteByUserId(userId,chapterId);
			
			System.out.println(JsonUtil.objectToJson(noteList));
			mv.addObject(noteList);
			mv.addObject("noteCount", noteList.size());
			mv.setViewName("/backend/cp_videoWork");
		}catch (Exception e){
			e.printStackTrace();
			mv.setViewName("redirect:/index");
		}
	
		return mv;
	}
	
	
	
	//笔记发表
	@RequestMapping(value="/addNotes", method=RequestMethod.POST)
	public ModelAndView addNotes(@RequestParam(value="noteTitle", required=false) String noteTitle,
			@RequestParam(value="noteContent", required=false) String noteContent,
			@RequestParam(value="chapterId", required=false) String chapterId,
			@RequestParam(value="userId", required=false) String userId,
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try{
			//保存笔记
			Note note = new Note();
			note.setTitle(noteTitle);
			note.setContent(noteContent);
			note.setChapterId(Integer.parseInt(chapterId));
			noteService.save(note);

			//保存用户与笔记的关系
			noteService.saveNoteAndUserRelation(note.getId(), Integer.valueOf(userId));
			mv.setViewName("/backend/cp_videoWork");
		} catch (NullPointerException e){
			e.printStackTrace();
			mv.setViewName("/backend/login");
		}catch (Exception e){
			e.printStackTrace();
			mv.setViewName("/backend/index");
		}
		
		return mv;
		
	}
	
	
	//用户笔记展示Ajax
	@RequestMapping(value="/userNoteList", method=RequestMethod.POST)
	public void userNoteList(
			@RequestParam(value="userId", required=false) String userId,
			@RequestParam(value="chapterId", required=false) String chapterId,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			String info;
			Map<String,Object> map=new HashMap<String,Object>();
			List<Note> noteList=new ArrayList<>();
			
			if(!userId.equals("")){
				
				//查找该用户在当前章节所有笔记
				noteList = noteService.findNoteByUserId(Integer.parseInt(userId),Integer.parseInt(chapterId));
				
				map.put("info", "success");
				map.put("date", noteList);
			}else{
				map.put("info", "请登录");
			}
			
			writer.write(JsonUtil.objectToJson(noteList));
			writer.flush();
			writer.close();
	}
	
	//用户笔记发表Ajax
	@RequestMapping(value="/addNote", method=RequestMethod.POST)
	public void addNote(@RequestParam(value="noteTitle", required=false) String noteTitle,
			@RequestParam(value="noteContent", required=false) String noteContent,
			@RequestParam(value="userId", required=false) String userId,
			@RequestParam(value="chapterId", required=false) String chapterId,
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter writer = response.getWriter();
			String info;
			Map<String,Object> map=new HashMap<String,Object>();
			
			if(!userId.equals("")){
				//保存笔记
				Note note = new Note();
				note.setTitle(noteTitle);
				note.setContent(noteContent);
				note.setChapterId(Integer.parseInt(chapterId));
				noteService.save(note);
	
				//保存用户与笔记的关系
				noteService.saveNoteAndUserRelation(note.getId(), Integer.valueOf(userId));
				
				map.put("info", "success");
			}else{
				map.put("info", "请登录");
			}
			
			writer.write(JsonUtil.objectToJson(map));
			writer.flush();
			writer.close();
	}
	
	
	//视频章节页面
	@RequestMapping(value="/course_videoChapter", method=RequestMethod.GET)
	public ModelAndView videoChapterPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/cp_videoChapter");
		return mv;
	}
}
