package org.ranji.lemon.volador.controller.personal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonalCenterController {
	
	/*
	 * 个人中心 - 评论留言
	 * */
	@RequestMapping(value="/personalCenter_notice", method=RequestMethod.GET)
	public ModelAndView personalCenterNotice(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_notice");
		return mv;
	}
	
	/*
	 * 个人中心 - 评论 
	 * */
	@RequestMapping(value="/personalCenter_comment", method=RequestMethod.GET)
	public ModelAndView personalCenterComment(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_comment");
		return mv;
	}
	
	
	/*
	 * 个人中心 - 正在学习的课程 
	 * */
	@RequestMapping(value="/personalCenter_learning", method=RequestMethod.GET)
	public ModelAndView personalCenterLearn_now(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_learn_now");
		return mv;
	}
	
	/*
	 * 个人中心 - 已学习完的课程 
	 * */
	@RequestMapping(value="/personalCenter_learn_end", method=RequestMethod.GET)
	public ModelAndView personalCenterLearn_end(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_learn_end");
		return mv;
	}
	
	/*
	 * 个人中心 - 收藏课程 
	 * */
	@RequestMapping(value="/personalCenter_fiavourite_course", method=RequestMethod.GET)
	public ModelAndView personalCenterFiavourite_course(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wzq_fiavourite_end");
		return mv;
	}
	
	/*
	 * 个人中心 - 资料下载 
	 * */
	@RequestMapping(value="/personalCenter_data_download", method=RequestMethod.GET)
	public ModelAndView personalCenterData_download(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_data_download");
		return mv;
	}
	
	/*
	 * 个人中心 - 积分页面
	 * */
	@RequestMapping(value="/personalCenter_jifen", method=RequestMethod.GET)
	public ModelAndView personalCenterJifen(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wzq_jifen");
		return mv;
	}
	
	/*
	 * 个人中心 - 积分抽奖规则 
	 * */
	@RequestMapping(value="/personalCenter_draw_rule", method=RequestMethod.GET)
	public ModelAndView personalCenterDraw_rule(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_draw_rule");
		return mv;
	}
	
	/*
	 * 个人中心 - 我的作业
	 * */
	@RequestMapping(value="/personalCenter_homework", method=RequestMethod.GET)
	public ModelAndView personalCenterHomework(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wzq_list_work_upload");
		return mv;
	}
	
	/*
	 * 个人中心 - 作业上传
	 * */
	@RequestMapping(value="/personalCenter_uploadHomework", method=RequestMethod.GET)
	public ModelAndView personalCenterUploadHomework(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_upload_work");
		return mv;
	}
	
	/*
	 * 个人中心 - 职业导航
	 * */
	@RequestMapping(value="/personalCenter_zhiye", method=RequestMethod.GET)
	public ModelAndView personalCenterZhiye(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wzq_zhiye");
		return mv;
	}
	
	/*
	 * 个人中心 - 作业展示
	 * */
	@RequestMapping(value="/personalCenter_showHomework", method=RequestMethod.GET)
	public ModelAndView personalCenterShowHomework(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wzq_show_work");
		return mv;
	}
	
	
	/*
	 * 个人中心 - 邀请码
	 * */
	@RequestMapping(value="/personalCenter_invitation", method=RequestMethod.GET)
	public ModelAndView personalCenterInvitation(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_invitation");
		return mv;
	}
	
	
	

}
