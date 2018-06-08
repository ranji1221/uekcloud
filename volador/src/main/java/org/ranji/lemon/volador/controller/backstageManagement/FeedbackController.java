package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.ResultMap;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.service.global.prototype.IFeedbackService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FeedbackController {
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IFeedbackService feedbackService;
	
	/**
	 * 
	 */
	@RequestMapping(value="/feedback", method=RequestMethod.GET)
	public void findFeedbackByPage(HttpServletRequest request,HttpServletResponse response)throws IOException{
		response.setHeader("content-type", "application/json,charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map result = new HashMap<>();
		try {
			
			if(adminService.parseJWT(request.getHeader("token"))){
			 int page = 0;
			 int limit = 0;
			 if(request.getParameter("page")!=null){
				 page = Integer.parseInt(request.getParameter("page"));
			 }else{
				 page = 1;
			 }
			 limit = Integer.parseInt(request.getParameter("limit"));
			 result = feedbackService.findFeedBackByPage(page, limit);
			 result.put("code", 200);
			 result.put("message", "获取成功");	
			}else{
				result.put("code", 404);
				result.put("message", "非法请求");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result.put("code", 404);
			result.put("message", "获取失败");
		}
		pw.println(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();
		
	}

}
