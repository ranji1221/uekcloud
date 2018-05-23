package org.ranji.lemon.volador.controller.personal;

import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.IUserInfoService;
import org.ranji.lemon.volador.util.SmsBase;
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
		
		try{
			//输入参数有效性检查
			Boolean isLegal = CheckValid(username, password1);
			if(!isLegal || !password1.equals(password2)){
				mv.addObject("message", "用户名或密码错误！");

			}else if(!checkCode(request.getSession().getAttribute("code").toString(), code)){
				//验证短信验证码
				mv.addObject("message", "验证码错误!");

			}
			else{
				//查看用户是否已经注册
				Per user = personalService.findByUserName(username);
				if(null != user){
					mv.addObject("message", "用户已经注册");	

				}else{				
					//保存用户
					user = new Per();
					user.setUsername(username);
					user.setPassword(password1);
					
	                personalService.save(user);
					
					//保存用户对应的用户信息，默认为空
					UserInfo userInfo = new UserInfo();
					
					//设置默认头像
					userInfo.setHead_image("photos\\wqf_user.png");
					//设置默认昵称
					userInfo.setNickname("飞鱼学员" + String.valueOf((int)(Math.random()*1000)));
					userInfoService.save(userInfo);
					
					//保存用户关系表
					personalService.saveUserAndUserInfoRelation(user.getId(), userInfo.getId());
					mv.setViewName("redirect:/login");	
					//用户注册成功，返回登录就界面
					return mv;					
				}
			}
			
		} catch (RuntimeException e){
			mv.addObject("message", "短信验证失败!");
			e.printStackTrace();
		}catch (Exception e){
			mv.addObject("message", "注册异常：注册用户失败!");
			e.printStackTrace();
		}
		mv.setViewName("/backend/cp_register");
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
	
	
	@RequestMapping(value = "/register_sms", method = RequestMethod.POST)
	public void sms(HttpServletRequest request, HttpServletResponse response) throws Exception {      
        
        String result = "0";      
        /** 手机号码 */      
        Object jbPhone=request.getParameter("jbPhone");     
        System.out.println("jbphone------------------------"+jbPhone);  
        /** 短信验证码 */      
        Object code = request.getParameter("code");  
        System.out.println("code---------------------------"+code);  
        /** 短信验证码存入session(session的默认失效时间30分钟) */      
        request.getSession().setAttribute("code", code.toString());      
     
        /** 单个手机号发送短信的方法的参数准备 */      
        // 手机号码      
        String mobilephone = jbPhone.toString();      
        // 短信内容+随机生成的6位短信验证码      
        String content = "【飞鱼平台】注册验证码为:" + code.toString();//自定义发送的内容  </span>  
            
            /** 单个手机号发送短信 */      
           if (!sendMessage(content, mobilephone)) {      
                result = "0";// 失败      
            } else {      
                result = "1";// 成功      
                }      
        
        PrintWriter out = response.getWriter();      
        out.write(result.toString());     
    }
	
	public static boolean sendMessage( String content, String mobileNumber) {      
        SmsBase smsBase = new SmsBase();  
       // 单个手机号码发送      
       try {      
           String  retObj = smsBase.SendSms(mobileNumber, content);  
           System.out.println("in sendMessage -------------------------");  
           //System.out.println(retObj);    
           if (retObj == "未发送，编码异常") {       
               return false;      
           } else {      
               return true;      
           }     
       } catch (Exception ex) {      
           ex.printStackTrace();       
       }      
       return true;      
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
	/*
	// 验证短信验证码是否正确     
	@RequestMapping(value = "/register_checkCode", method = RequestMethod.POST)
    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws Exception{      
        String result = "0";      
        // 获取手动输入的手机短信验证码     
        String SmsCheckCode = (String)(request.getParameter("SmsCheckCode"));       
        // 获取session中存放的手机短信验证码      
        Object code =request.getSession().getAttribute("code");      
        try {      
            if(SmsCheckCode != code.toString() && !SmsCheckCode.equals(code.toString())){      
                result = "0";      
            }else{      
                result = "1";      
            }      
        } catch (Exception e) {      
            throw new RuntimeException("短信验证失败", e);      
        }       
        PrintWriter out = response.getWriter();      
        out.write(result.toString());      
    }*/
}
