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
import org.ranji.lemon.volador.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class adminLoginController {
	
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
		
		Map<String,Object> result=new HashMap<String,Object>();
		
		if(adminService.judgeLogin(username, password)){
			result.put("code", 200);
			result.put("message", "认证成功");
			Map<String,Object> data=new HashMap<String,Object>();
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
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public ModelAndView adminIndex(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("admin/index");
		
		return mv;
		
	}
}
