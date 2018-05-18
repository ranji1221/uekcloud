package org.ranji.lemon.volador.controller.personal;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController {

	@Autowired
	private IPerService personalService;
	
	@Autowired
	private IUserInfoService userInfoService;
		
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView registerPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/cp_register");
		return mv;
	}
	
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView register(
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value="pass1",required=false) String password1,
			@RequestParam(value="pass2",required=false) String password2,
			HttpSession session,
			HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		//输入参数有效性检查
		Boolean isLegal = CheckValid(username, password1, code);
		if(!isLegal || !password1.equals(password2)){
			mv.addObject("message", "Parameter illega");
			mv.setViewName("redirect:/register");
			return mv;
		}
		//查看用户是否已经注册
		Per user = personalService.findByUserName(username);
		if(null != user){
			mv.addObject("message", "用户已经注册");	
			mv.setViewName("redirect:/register");
		}
		else{
			//保存用户
			user = new Per();
			user.setUsername(username);
			user.setPassword(password1);
			try{
				personalService.save(user);
				
				//保存用户对应的用户信息，默认为空
				UserInfo userInfo = new UserInfo();
				
				//设置默认头像
				userInfo.setHead_image("photos\\wqf_user.png");
				userInfoService.save(userInfo);
				
				//保存用户关系表
				personalService.saveUserAndUserInfoRelation(user.getId(), userInfo.getId());
				
				mv.setViewName("redirect:/login");
			} catch (Exception e){
				mv.addObject("message", "register errors");
				mv.setViewName("redirect:/register");
			}
		}
				
		return mv;
	}

	/**
	 * 参数有效性检查
	 * @param username  用户名
	 * @param password  密码
	 * @param code      验证码
	 * @return 参数合法，返回true，否则返回false
	 */
	public Boolean CheckValid(String username, String password, String code){
		
		//验证用户名是否合法
		String regEx = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(username);
		boolean rs = matcher.matches();
		if (!rs){
			return false;
		}
				
		//检查密码是否合法
		regEx = "^[^\\s]{6,20}$";
		pattern = Pattern.compile(regEx);
		matcher = pattern.matcher(password);
		rs = matcher.matches();
		if(!rs){
			return false;
		}
		
		return true;
	}
}
