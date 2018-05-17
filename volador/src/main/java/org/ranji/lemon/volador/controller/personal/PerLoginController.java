package org.ranji.lemon.volador.controller.personal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PerLoginController {
	@Autowired
	private IPerService personalService;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPage(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/backend/cp_login");
		return mv;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView login(@RequestParam(value="username",required=false) String username,
			@RequestParam(value="password",required=false) String password,
			HttpSession session,
			HttpServletRequest request){
		
		//参数合法性检查
		CheckValid(username, password);
		ModelAndView mv = new ModelAndView();

		//String kaptchaExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		Subject subject = SecurityUtils.getSubject();	
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try{
			subject.login(token);
			request.getSession().setAttribute("userId", personalService.findByUserName(username).getId());
			request.getSession().setAttribute("userName", username);
			mv.setViewName("redirect:/index");
		} catch (AuthenticationException e){
			mv.addObject("message", "login errors");
			mv.setViewName("redirect:/login");
			e.printStackTrace();
		} 

		return mv;
	}
	
	//退出登录
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request){
		
		ModelAndView mv = new ModelAndView();
		HttpSession session =  request.getSession();
		if(null != session){
			session.removeAttribute("userName");
		}

		mv.setViewName("redirect:/index");
		return mv;
	}
	
	/**
	 * 参数有效性检查
	 * @param username  用户名
	 * @param password  密码
	 * @param code      验证码
	 * @return 参数合法，返回true，否则返回false
	 */
	public Boolean CheckValid(String username, String password){
		
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
