package org.ranji.lemon.volador.controller.personal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonalController {
	@Autowired
	private IPerService userService;
	@Autowired
	private IUserInfoService userInfoService;
	
	//首页
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView indexPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/index");
		return mv;
	}
	//基本资料
	@RequestMapping(value="/personal_basic", method=RequestMethod.GET)
	public ModelAndView personalBasicPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/wqf_personal_basic");
		return mv;
	}
	@RequestMapping(value="/personal_basic", method=RequestMethod.POST)
	public ModelAndView personalBasic(
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="realname",required=false) String realname,
			@RequestParam(value="nickname",required=false) String nickname,
			@RequestParam(value="sex",required=false) String sex,
			@RequestParam(value="qq",required=false) String qq,
			@RequestParam(value="wechat",required=false) String wechat){
		ModelAndView mv = new ModelAndView();
		if (null != username){
			Per user = userService.findByUserName(username);
			if(null != user){
				UserInfo userinfo = new UserInfo();
				userinfo.setGender(sex);
				userinfo.setNickname(nickname);
				userinfo.setQQ(qq);
				userinfo.setReal_name(realname);
				userinfo.setWechat(wechat);
				//保存用户信息
				userInfoService.saveUserInfo(userinfo);
				
				//保存用户与用户信息
				userService.saveUserAndUserInfoRelation(user.getId(), userinfo.getId());
				mv.addObject(userinfo);			
			}
		}			
	
		mv.setViewName("/backend/wqf_personal_basic");
		return mv;
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

}
