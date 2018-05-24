package org.ranji.lemon.volador.controller.course;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Comment;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Note;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.course.prototype.IChapterService;
import org.ranji.lemon.volador.service.course.prototype.IChapterTitleService;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.ICommentService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.course.prototype.INoteService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
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
	private IPerService personalService;
	
	@Autowired
	private IChapterTitleService chapterTitleService;
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private INoteService noteService;
	
	
	//职业导航
	@RequestMapping(value="/professionalNavigation", method=RequestMethod.GET)
	public ModelAndView professionalNavigationPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/cp_zhiyedaohang");
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
	public ModelAndView findCoursePage(){
		ModelAndView mv = new ModelAndView();
		
		//界面显示所有分类
		List<Classify> classfyList = classifyService.findAll();
		mv.addObject(classfyList);
		
		//界面显示课程
		List<Course> courseList = courseService.findAll();
		mv.addObject(courseList);
		
		
		mv.setViewName("/backend/wqf_find");
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
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("login_yes","login_yes active");
			mv.addObject("login_no","login_no");
			mv.addObject("userName", userName);
		}
		catch (Exception e) {
			mv.addObject("login_yes","login_yes");
			mv.addObject("login_no","login_no active");
		}
		
		
		if(null != courseId){
			//查询课程
			Course course=courseService.find(courseId);
			
			if(course.getCourse_image_address()==null){
				course.setCourse_image_address("images/cp_10.png");
			}
			//将课程信息返回
			mv.addObject(course);
			
			//查找课程对应教师
			Teacher teacher=courseService.findTeacherbyCourse(courseId).get(0);
			
			//返回教师信息
			mv.addObject(teacher);
			
			//根据课程id查询章节标题集合
			List<ChapterTitle> chapterTitleList=courseService.findChapterTitleByCourse(courseId);
			Map<String,Object> chapterListMap=new HashMap<String,Object>();
			for(int i=0;i<chapterTitleList.size();i++){
				//根据章节标题id查询章节
				chapterListMap.put("chapter"+i,chapterTitleService.findChapterByChapterTitle(chapterTitleList.get(i).getId()));
			}
			
			mv.addAllObjects(chapterListMap);
			mv.addObject(chapterTitleList);
			//根据课程ID查询章节
//			List <Chapter> chapterList = courseService.findChapterbyCourse(courseId);
//			//将章节列表返回给前台
//			mv.addObject(chapterList);
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
			mv.addObject("login_yes","login_yes");
			mv.addObject("login_no","login_no active");
		}
		if(null != courseId){
			//查询课程
			Course course=courseService.find(courseId);
			
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
	
	//课程发表评论
		@RequestMapping(value="/course_addcomment",method=RequestMethod.POST)
		public void courseComment(@RequestParam(value="content", required=false) String content,
				@RequestParam(value="chapterId", required=false) String courseId,
				@RequestParam(value="userId", required=false) String userId,
				HttpServletRequest request,
				HttpServletResponse response){
			//设置返回格式
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			
			String info="用户未登录，请登录！";
			Map<String,Object> map=new HashMap<String,Object>();
			
			try {			
				System.out.println(userId);
				
				Comment comment=new Comment();
				
				//保存评论
				comment.setContent(content);
				commentService.save(comment);
				
				//保存评论和课程的关系
				commentService.saveCourseAndCommentRelation(comment.getId(), Integer.parseInt(courseId.toString()));
				
				//保存评论和用户关系
				commentService.savaCommentAndUserRelation(comment.getId(), Integer.parseInt(userId.toString()));
				
				info = "评论发布成功！";
				map.put("info", info);
				
				PrintWriter writer = response.getWriter();
				writer.print(JsonUtil.toJsonByProperties(map)); 
		        writer.flush();  
		        writer.close();
				
			} catch (NullPointerException e){
				e.printStackTrace();
				info = "空指针异常！";
				map.put("info",info);
			}catch (NumberFormatException e){
				e.printStackTrace();
			}catch (Exception e) {
				info = "异常！";
				map.put("info",info);
				e.printStackTrace();
			}

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
			mv.setViewName("redirect:/index");
			return mv;
		}
		
		try {
			//判断用户是否登录，获取用户登录信息
			int userId=(int) request.getSession().getAttribute("userId");
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject(userInfo);
			mv.addObject("userId", userId);
			mv.addObject("login_yes","login_yes active");
			mv.addObject("login_no","login_no");
		} catch (Exception e) {
			// TODO: handle exception
			UserInfo userInfo=new UserInfo();
			userInfo.setHead_image("images/wzq_user_img.jpg");
			mv.addObject(userInfo);
			mv.addObject("login_yes","login_yes");
			mv.addObject("login_no","login_no active");
		}

		try {
			//查询章节信息
			Chapter chapter=chapterService.find(chapterId);
			
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
			

			//传参
			mv.addObject("commentCount",commentList.size());
			mv.addObject(chapter);
			mv.addObject(commentList);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		//指定课程路径-----测试		
//		chapter.setChapter_name("章节：chapter");
//		chapter.setChapter_info("章节：章节详情");
//		chapter.setVideo_address("video/王菲 - 匆匆那年.mp4");		
		
		mv.setViewName("/backend/cp_video");
		return mv;
	}
	
	
	//章节发表评论
	@RequestMapping(value="/course_chapter_comment",method=RequestMethod.POST)
	public void chapterComment(@RequestParam(value="content", required=false) String content,
			@RequestParam(value="chapterId", required=false) String chapterId,
			@RequestParam(value="userId", required=false) String userId,
			HttpServletRequest request,
			HttpServletResponse response){
		
		String info="用户未登录，请登录！";
		Map<String,Object> map=new HashMap<String,Object>();
		
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
			
			info = "评论发布成功！";
			map.put("info", info);
			
			PrintWriter writer = response.getWriter();
			writer.print(JsonUtil.toJsonByProperties(map)); 
	        writer.flush();  
	        writer.close();
			
		} catch (NullPointerException e){
			e.printStackTrace();
			info = "空指针异常！";
			map.put("info",info);
		}catch (NumberFormatException e){
			e.printStackTrace();
		}catch (Exception e) {
			info = "异常！";
			map.put("info",info);
			e.printStackTrace();
		}

	}
	
	//视频笔记页面
	@RequestMapping(value="/course_chapterNote", method=RequestMethod.GET)
	public ModelAndView videoWorkPage(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		try{
			int userId=(int) request.getSession().getAttribute("userId");
			mv.addObject("userId", userId);
			mv.setViewName("/backend/cp_videoWork");
		}catch (Exception e){
			e.printStackTrace();
			mv.setViewName("redirect:/login");
		}
	
		return mv;
	}
	
	@RequestMapping(value="/addNotes", method=RequestMethod.POST)
	public ModelAndView addNotes(@RequestParam(value="noteTitle", required=false) String noteTitle,
			@RequestParam(value="noteContent", required=false) String noteContent,
			@RequestParam(value="userId", required=false) String userId,
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try{
			//保存笔记
			Note note = new Note();
			note.setTitle(noteTitle);
			note.setContent(noteContent);
			noteService.save(note);

			//保存用户与笔记的关系
			noteService.saveNoteAndUserRelation(note.getId(), Integer.valueOf(userId));
			mv.setViewName("/backend/cp_videoWork");
		} catch (Exception e){
			e.printStackTrace();
			mv.setViewName("/backend/login");
		}
		
		return mv;
		
	}
	
	//视频章节页面
	@RequestMapping(value="/course_videoChapter", method=RequestMethod.GET)
	public ModelAndView videoChapterPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/cp_videoChapter");
		return mv;
	}
}
