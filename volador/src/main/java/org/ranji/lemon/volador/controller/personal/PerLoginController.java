package org.ranji.lemon.volador.controller.personal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.subject.Subject;
import org.ranji.lemon.core.util.DateUtil;
import org.ranji.lemon.volador.model.personal.Per;
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
		mv.setViewName("backend/cp_login");
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

		//用户是否已注册
		Per user = personalService.findByUserName(username);
		
		if(null == user){
			mv.addObject("message", "用户未注册");
			mv.setViewName("backend/cp_login");
		}else{
			Subject subject = SecurityUtils.getSubject();	
			
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try{
				subject.login(token);
				request.getSession().setAttribute("userId", personalService.findByUserName(username).getId());
				request.getSession().setAttribute("userName", username);
				mv.setViewName("redirect:/index");
			} catch (AuthenticationException e){
				mv.addObject("message", "用户名或密码不正确");
				mv.setViewName("backend/cp_login");
				e.printStackTrace();
			}
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
			session.removeAttribute("userId");
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

	// 忘记密码
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
	public ModelAndView changePassword(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("backend/forgetPassword");
		return mv;
	}

	// 忘记密码
	@RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
	public ModelAndView setPassword(@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value="code",required=false) String code,
			@RequestParam(value = "passwordOld", required = false) String passwordOld,
			@RequestParam(value = "passwordNew", required = false) String passwordNew, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		try {
			//输入账号合法性检查
			String regEx = "^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$";
			Pattern pattern = Pattern.compile(regEx);
			Matcher matcher = pattern.matcher(userName);
			boolean rs = matcher.matches();
			if (rs){
				Per user = personalService.findByUserName(userName);
				//如果用户名存在
				if(null != user){
					//短信验证
					if(null ==request.getSession().getAttribute("code") || !checkCode(request.getSession().getAttribute("code").toString(), code)){
						//验证短信验证码
						mv.addObject("message", "验证码错误!");
						mv.setViewName("backend/forgetPassword");
					}else{
						// 新旧密码是否一致
						if (passwordOld.equals(passwordNew)) {
							// 检查密码是否合法
							regEx = "^[^\\s]{6,20}$";
							pattern = Pattern.compile(regEx);
							matcher = pattern.matcher(passwordNew);
							boolean bresult = matcher.matches();
							if (!bresult) {
								mv.addObject("messege", "新旧密码不一致");
								return mv;
							} else {
								// 清除session
								HttpSession session = request.getSession();
								session.invalidate();
								Subject subject = SecurityUtils.getSubject();
								subject.logout();
								user.setPassword(passwordNew);
								user.setUpdateTime(DateUtil.now());
								personalService.update(user);

								// 用户重新登录
								mv.setViewName("redirect:/login");
							}
							
						} else {
							mv.addObject("message", "两次输入新密码不一致");
							mv.setViewName("backend/forgetPassword");
						}
					}
					
				}else{
					mv.addObject("message", "账号不存在，请注册！");
					mv.setViewName("backend/forgetPassword");
				}
			}else{
				mv.addObject("message", "账号不合法！");
				mv.setViewName("backend/forgetPassword");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			mv.setViewName("redirect:/login");
		}

		return mv;
	}
	/**
	 * 验证短信验证码是否正确 
	 * @param smsCheckCode   手动输入的手机短信验证码
	 * @param code           session中存放的手机短信验证码
	 * @return               验证码正确返回True
	 */
	public Boolean checkCode(String smsCheckCode, String code){
		Boolean isValidCode = false;
		try{
			if(!smsCheckCode.isEmpty() && smsCheckCode.equals(code.toString())){
				isValidCode = true;
			}
		}catch (Exception e) {
			throw new RuntimeException("短信验证失败", e);
		}
		return isValidCode;
	}
}