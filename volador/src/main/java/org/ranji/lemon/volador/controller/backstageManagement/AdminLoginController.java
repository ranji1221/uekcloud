package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.personal.Admin;
import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.ranji.lemon.volador.util.GeetestConfig;
import org.ranji.lemon.volador.util.GeetestLib;
import org.ranji.lemon.volador.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author sertion
 * @date 2018/6/11
 * @since JDK1.8
 * @version 1.0
 */
@Controller
public class AdminLoginController {
	
	@Autowired
	private IAdminService adminService;
	
	@RequestMapping(value="/adminLogin", method=RequestMethod.POST)
	public void adminLogin(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws Exception{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
//		InputStream requestInputStream = request.getInputStream(); 
//		int lengthOfContent = request.getContentLength();  
//		String sss=convertStreamToString(requestInputStream,"utf-8",lengthOfContent);
//		Per user=JsonUtil.jsonToPojo(sss, Per.class);
		
		String username=map.get("username");
		String password=map.get("password");
		
//		String username=request.getParameter("username");
//		String password=request.getParameter("password");
		
		Map<String,Object> result=new HashMap<String,Object>(16);
		
		if(adminService.judgeLogin(username, password)){
			result.put("code", 200);
			result.put("message", "认证成功");
			Map<String,Object> data=new HashMap<String,Object>(16);
			Admin admin=adminService.findAdminByUsername(username);
			data.put("token", JWTUtil.createJWT(username, JsonUtil.objectToJson(admin), 1000*60*60*24));
			data.put("id", admin.getId());
			data.put("username", admin.getUsername());
			data.put("name", "管理员");
			result.put("data", data);
		}else{
			result.put("code", 404);
			result.put("message", "密码错误");
		}
		
		pw.print(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();
	}
	
	@RequestMapping(value="/uekadmin",method=RequestMethod.GET)
	public ModelAndView adminIndex(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("admin/index");
		
		return mv;
		
	}
	
	@RequestMapping(value="/adminInit",method=RequestMethod.GET)
	public void adminInit(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), 
				GeetestConfig.isnewfailback());
		
		String resStr = "{}";
		String userid = "test";
		
		//自定义参数,可选择添加
		HashMap<String, String> param = new HashMap<String, String>(16);
		//网站用户id
		param.put("user_id", userid); 
		//传输用户请求验证时所携带的IP
		param.put("ip_address", "127.0.0.1"); 
		//web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
		param.put("client_type", "web"); 
		
		//进行验证预处理
		int gtServerStatus = gtSdk.preProcess(param);
//		System.out.println(gtServerStatus);
//		//将服务器状态设置到session中
//		request.getSession().setAttribute(gtSdk.gtServerStatusSessionKey, gtServerStatus);
//		//将userid设置到session中
//		request.getSession().setAttribute("userid", userid);

		resStr = gtSdk.getResponseStr();
		
		Map<String,Object> result=new HashMap<String,Object>(16);
		result.put("data", resStr);
		result.put("gt_server_status_code", gtServerStatus);
		
//		System.out.println(resStr);
		
		pw.print(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();
		
	}
	
	@RequestMapping(value="/adminLoginJi",method=RequestMethod.POST)
	public void adminLoginJi(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws Exception{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		GeetestLib gtSdk = new GeetestLib(GeetestConfig.getGeetest_id(), GeetestConfig.getGeetest_key(), 
				GeetestConfig.isnewfailback());
		
//		String challenge = request.getParameter(GeetestLib.fn_geetest_challenge);
//		String validate = request.getParameter(GeetestLib.fn_geetest_validate);
//		String seccode = request.getParameter(GeetestLib.fn_geetest_seccode);
		String challenge = map.get(GeetestLib.fn_geetest_challenge);
		String validate = map.get(GeetestLib.fn_geetest_validate);
		String seccode = map.get(GeetestLib.fn_geetest_seccode);
		String username=map.get("username");
		String password=map.get("password");
		int gtServerStatusCode = Integer.parseInt(map.get("gt_server_status_code"));
		
		
//		从session中获取gt-server状态
//		System.out.println(request.getSession());
//		System.out.println(request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey));
//		System.out.println(request.getSession().getAttribute("userid"));
//		int gt_server_status_code = (int) request.getSession().getAttribute(gtSdk.gtServerStatusSessionKey);
//		//从session中获取userid
//		String userid = (String)request.getSession().getAttribute("userid");
		
		//自定义参数,可选择添加
		HashMap<String, String> param = new HashMap<String, String>(16); 
		//网站用户id
		param.put("user_id", "test"); 
		//web:电脑上的浏览器；h5:手机上的浏览器，包括移动应用内完全内置的web_view；native：通过原生SDK植入APP应用的方式
		param.put("client_type", "web"); 
		//传输用户请求验证时所携带的IP
		param.put("ip_address", "127.0.0.1"); 
		
		int gtResult = 0;
		if (gtServerStatusCode == 1) {
			//gt-server正常，向gt-server进行二次验证
			gtResult = gtSdk.enhencedValidateRequest(challenge, validate, seccode, param);
			System.out.println(gtResult);
		} else {
			// gt-server非正常情况下，进行failback模式验证
			System.out.println("failback:use your own server captcha validate");
			gtResult = gtSdk.failbackValidateRequest(challenge, validate, seccode);
			System.out.println(gtResult);
		}
		
		Map<String,Object> result=new HashMap<String,Object>(16);

		if (gtResult == 1) {
			// 验证成功
			if(adminService.judgeLogin(username, password)){
				result.put("code", 200);
				result.put("message", "认证成功");
				Map<String,Object> data=new HashMap<String,Object>(16);
				Admin admin=adminService.findAdminByUsername(username);
				data.put("token", JWTUtil.createJWT(username, JsonUtil.objectToJson(admin), 1000*60*60*24));
				data.put("id", admin.getId());
				data.put("username", admin.getUsername());
				data.put("name", "管理员");
				result.put("data", data);
			}else{
				result.put("code", 404);
				result.put("message", "密码错误");
			}
		}
		else {
			// 验证失败
			result.put("status", "404");
			result.put("message","验证码错误");
		}
		
		pw.print(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();
		
	}
}
