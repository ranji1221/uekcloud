package org.ranji.lemon.volador.controller.course;

import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {
	@Autowired
	private ICourseService courseService;
	
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
		mv.setViewName("/backend/wqf_find");
		return mv;
	}
	
	//课程章节
	@RequestMapping(value="/course_chapter", method=RequestMethod.GET)
	public ModelAndView chapterPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_chapter");
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
		ModelAndView mv = new ModelAndView();
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
}
