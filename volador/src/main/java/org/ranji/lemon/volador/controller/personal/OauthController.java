package org.ranji.lemon.volador.controller.personal;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.personal.AuthUser;
import org.ranji.lemon.volador.model.personal.Integral;
import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.service.personal.impl.AuthUserServiceImpl;
import org.ranji.lemon.volador.service.personal.prototype.IAuthUserService;
import org.ranji.lemon.volador.service.personal.prototype.IIntegralService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.ISignInService;
import org.ranji.lemon.volador.service.personal.prototype.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.stereotype.Controller;  
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.PageFans;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.PageFansBean;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth; 
@Controller
public class OauthController {
	
	@Autowired
	private IAuthUserService authUserService;
	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private IPerService personalService;
	@Autowired
	private ISignInService signInService;
	
	@Autowired
	private IIntegralService integralService;
	
	@RequestMapping(value="/qqLogin", method=RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {  
        response.setContentType("text/html;charset=utf-8");  
        try { 
        	Oauth oauth = new Oauth();
        	oauth.getAuthorizeURL(request);
            response.sendRedirect(oauth.getAuthorizeURL(request));  
        } catch (QQConnectException e) {  
            e.printStackTrace();  
        }  
  
        return null;  
    }

	/**
     * 用于QQ登录的回调
     */
    @RequestMapping(value = "/afterQQLogin", method=RequestMethod.GET)
    protected ModelAndView afterQQLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");

        PrintWriter out = response.getWriter();
        ModelAndView mv = new ModelAndView();
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

            String accessToken = null, openID = null;
            long tokenExpireIn = 0L;

            if (accessTokenObj.getAccessToken().equals("")) {
//                我们的网站被CSRF攻击了或者用户取消了授权
//                做一些数据统计工作
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj = new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();

                request.getSession().setAttribute("demo_openid", openID);
                // 利用获取到的accessToken 去获取当前用户的openid --------- end
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                	String identity_type = "qq";
                	if(authUserService.isFirstLogin(identity_type, openID)){
                		//用户第一次登陆，保存用户信息
                		org.ranji.lemon.volador.model.personal.UserInfo userInfoBind = new org.ranji.lemon.volador.model.personal.UserInfo();
                		userInfoBind.setNickname(userInfoBean.getNickname());
                		userInfoBind.setGender(userInfoBean.getGender());
                		//设置默认头像
                		userInfoBind.setHead_image("images/wzq_user_img.jpg");
                		userInfoService.save(userInfoBind);
                		//创建用户表
                		Per person = new Per();
                		person.setUsername(userInfoBean.getNickname());
                		person.setPassword(accessToken);
                		personalService.save(person);
                		//保存用户关系表
    					personalService.saveUserAndUserInfoRelation(person.getId(), userInfoBind.getId());
    					
                		//初始化签到表
    					SignIn sigIn = new SignIn();
    					sigIn.setUserId(person.getId());
    					sigIn.setDay(0);
    					signInService.save(sigIn);
    					
    					//初始化积分表
    					Integral integral = new Integral();
    					integral.setUserId(person.getId());
    					integral.setIntegralNumber(0);
    					integralService.save(integral);
    					
                		AuthUser authUser = new AuthUser();
                    	authUser.setIdentity_type(identity_type);
                    	authUser.setIdentifier(openID);
                    	authUser.setCredential(accessToken);
                    	authUser.setUserinfo_id(userInfoBind.getId());
                		authUserService.save(authUser);
                		request.getSession().setAttribute("userId", person.getId());
        				request.getSession().setAttribute("userName", userInfoBean.getNickname());
                	}else{
                		//用户不是第一次登陆
                		AuthUser authUser = authUserService.findAuthUser(identity_type, openID);
                		if(null != authUser){
                			authUser.setIdentity_type(identity_type);
                        	authUser.setIdentifier(openID);
                        	authUser.setCredential(accessToken);
                        	authUserService.update(authUser);
                        	
                        	org.ranji.lemon.volador.model.personal.UserInfo userInfoBind = userInfoService.find(authUser.getUserinfo_id());
                        	if(null != userInfoBind){
                        		userInfoBind.setNickname(userInfoBean.getNickname());
                        		userInfoBind.setGender(userInfoBean.getGender());
                        		userInfoService.update(userInfoBind);
                        		Per person = personalService.findByUserName(userInfoBean.getNickname());
                        		request.getSession().setAttribute("userId", person.getId());
                				request.getSession().setAttribute("userName", userInfoBean.getNickname());
                        	}else{
                        		System.out.println("获取信息失败");
                        	}
                		}else{
                    		System.out.println("获取信息失败");
                    	}
                	}
                	
                	mv.setViewName("redirect:/index");
                } else {
                    out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
                }
            }
        } catch (QQConnectException e) {
        }
        
        return mv;
    }
}
