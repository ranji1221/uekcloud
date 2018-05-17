package org.ranji.lemon.volador.controller.personal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonalController {
	@Autowired
	private IPerService personalService;
	@Autowired
	private IUserInfoService userInfoService;
	
	//首页
	@RequestMapping(value="/index", method=RequestMethod.GET)
	public ModelAndView indexPage(HttpServletRequest request ){
		ModelAndView mv = new ModelAndView();
		try{
			String userName = request.getSession().getAttribute("userName").toString();
			mv.addObject("userName", userName);
		}
		catch (Exception e) {
			mv.addObject("userName", "游客");
		}

		mv.setViewName("/backend/index");
		return mv;
	}
	//基本资料
	@RequestMapping(value="/personal_basic", method=RequestMethod.GET)
	public ModelAndView personalBasicPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try{
			String userName = request.getSession().getAttribute("userName").toString();
			//获取用户
			Per person = personalService.findByUserName(userName);
			
			//获取用户信息
			UserInfo userInfo = userInfoService.find(personalService.findUserUserInfoRelationByUserId(person.getId()).get(0));
			
			//返回页面需要显示的用户信息
			mv.addObject("userName", userName);
			mv.addObject(userInfo);
		}
		catch (Exception e) {
			mv.addObject("userName", "游客");
			mv.addObject(new UserInfo());
		}		
		
		mv.setViewName("/backend/wqf_personal_basic");
		return mv;
	}
	@RequestMapping(value="/personal_basic", method=RequestMethod.POST)
	public ModelAndView personalBasic(
			@RequestParam(value="photo",required=false) MultipartFile file,
			@RequestParam(value="username",required=false) String username,
			@RequestParam(value="realname",required=false) String realname,
			@RequestParam(value="nickname",required=false) String nickname,
			@RequestParam(value="sex",required=false) String sex,
			@RequestParam(value="qq",required=false) String qq,
			@RequestParam(value="wechat",required=false) String wechat,
			HttpServletRequest request){
		
		
		
		ModelAndView mv = new ModelAndView();
		if (null != username){
			Per user = personalService.findByUserName(username);
			if(null != user){

				UserInfo userinfo = personalService.findUserInfoByUserId(user.getId());
				String head_image = saveFile(username, file);
				userinfo.setHead_image(head_image);
				userinfo.setGender(sex.equals("on")?"男":"女");
				userinfo.setNickname(nickname);
				userinfo.setQQ(qq);
				userinfo.setReal_name(realname);
				userinfo.setWechat(wechat);
				
				//更新用户信息
				userInfoService.update(userinfo);
				
				mv.addObject(userinfo);			
			}
		}			
	
		mv.setViewName("/backend/wqf_personal_basic");
		return mv;
	}

	//文件保存到后台
	private String saveFile(String username,
            MultipartFile file) {
		String filePath = "";
		
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
            	//获取文件后缀名
            	String fileName = file.getName();
        		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        		//如果用户未登录或session过期则给默认头像
                if(null == username || ("").equals(username)){
                	username = "wqf_user";
                	suffix = ".png";
                }
            	filePath = "photos\\" + username+ suffix;
               
                File saveDir = new File("E:\\JAVA_WORKSPACE\\UNIQUE\\uekcloud\\volador\\src\\main\\resources\\static\\" + filePath);
                if (!saveDir.getParentFile().exists())
                    saveDir.getParentFile().mkdirs();

                // 转存文件
                file.transferTo(saveDir);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filePath;
    }
	
	//账号资料
	@RequestMapping(value="/personal_set", method=RequestMethod.GET)
	public ModelAndView personalSetPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();

		String userName = request.getSession().getAttribute("userName").toString();
		mv.addObject("userName", userName);
		mv.addObject("head_image", "F:\\图片\\1524414791235.jpg");
		mv.setViewName("/backend/wqf_personal_set");
		return mv;
	}
	@RequestMapping(value="/personal_set", method=RequestMethod.POST)
	public ModelAndView personalSet(
			HttpServletRequest request){
		ModelAndView mv = new ModelAndView();	

		String userName = request.getSession().getAttribute("userName").toString();
		
		//获取用户
		//Per person = personalService.findByUserName(userName);
		
		
		mv.addObject("userName", userName);
		mv.addObject("head_image", "F:\\图片\\1524414791235.jpg");
		mv.setViewName("/backend/wqf_personal_set");
		return mv;
	}
	
	//点击课程，跳转到章节页面
	@RequestMapping(value="course", method=RequestMethod.GET)
	public ModelAndView coursePage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/course/chapter");
		return mv;
	}

}
