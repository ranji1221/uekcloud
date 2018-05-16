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

}
