package org.ranji.lemon.volador.controller.course;

import java.util.List;

import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.service.course.prototype.IChapterService;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
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
		
		//添加课程---测试
		Course course = new Course();
		course.setCourse_name("PYTHON");
		course.setCourse_info("优美的PYTHON");
		course.setCourse_price(888);
		course.setStudent_count(999);
		
		course.setCourse_name("JAVA");
		course.setCourse_info("厉害的JAVA");
		course.setCourse_price(666);
		course.setStudent_count(777);
		
		//courseService.save(course);
		addCourseClassify();
		
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
	public ModelAndView chapterPage(@RequestParam(value="courseId", required=false) Integer courseId){
		
		ModelAndView mv = new ModelAndView();
		
		if(null != courseId){
			//根据课程ID查询章节
			List <Chapter> chapterList = courseService.findChapterbyCourse(courseId);
			//将章节列表返回给前台
			mv.addObject(chapterList);
			mv.setViewName("/backend/wqf_chapter");
		}
		else{
			mv.addObject("error", "operate error!");
		}
												
		return mv;
	}
	
	//课程评论页面
	@RequestMapping(value="/course_comment", method=RequestMethod.GET)
	public ModelAndView commentPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/cp_comment");
		return mv;
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
	public ModelAndView videoPage(){
		
		//指定课程路径-----测试		
		Chapter chapter = new Chapter();
		chapter.setChapter_name("章节：chapter");
		chapter.setChapter_info("章节：章节详情");
		chapter.setVideo_address("video\\王菲 - 匆匆那年.mp4");
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject(chapter);
		
		mv.setViewName("/backend/cp_video");
		return mv;
	}
	
	//视频笔记页面
	@RequestMapping(value="/course_chapterNote", method=RequestMethod.GET)
	public ModelAndView videoWorkPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/cp_videoWork");
		return mv;
	}
	
	//视频章节页面
	@RequestMapping(value="/course_videoChapter", method=RequestMethod.GET)
	public ModelAndView videoChapterPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/cp_videoWork");
		return mv;
	}
	
	//添加课程分类
	public void addCourseClassify(){
		Classify classfy = new Classify();
		classfy.setClassify_name("AI");
		classifyService.save(classfy);
		classfy.setClassify_name("平面设计");
		classifyService.save(classfy);
		classfy.setClassify_name("移动界面");
		classifyService.save(classfy);
	}
}
