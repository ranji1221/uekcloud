package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.service.pay.prototype.IOrderService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class VoaldorCodeController {
	
		@Autowired
		private IAdminService adminService;
		
		@Autowired
		private IOrderService orderService;
		
	
		//分页获取CDkey
		@RequestMapping(value="/voladorCode", method=RequestMethod.GET)
		public void voladorCodeList(HttpServletRequest request,HttpServletResponse response) throws IOException{
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			Map result = new HashMap<>();
			if(adminService.parseJWT(request.getHeader("token"))){
				try{
					int page=Integer.parseInt(request.getParameter("page"));
					int limit=Integer.parseInt(request.getParameter("limit"));
					int status=Integer.parseInt(request.getParameter("status"));
					
					Map<String,Object> pageAndVoladorCode=orderService.pageVoladorCode(page,limit,status);
					result.put("code", 200);
					result.put("message", "获取成功");
					result.put("page", pageAndVoladorCode.get("page"));
					Map<String,Object> data=new HashMap<String,Object>();
					data.put("voladorCode", pageAndVoladorCode.get("voladorCode"));
					result.put("data", data);
					
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					result.put("code",404);
					result.put("message", "获取失败");
					
				}
				
			}
			else{
				result.put("code", "404");
				result.put("message", "非法请求");
			}
			
			pw.print(JsonUtil.objectToJson(result));
			pw.flush();
			pw.close();
			
		}
		
		//创建CDkey
		@RequestMapping(value="/voladorCode", method=RequestMethod.POST)
		public void addVoladorCode(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			Map result = new HashMap<>();
			if(adminService.parseJWT(request.getHeader("token"))){
				try{
					int courseId=Integer.parseInt(map.get("courseId"));
					int count=Integer.parseInt(map.get("count"));
					
					for(int i=0;i<count;i++){
						orderService.saveVoladorCode(courseId);
					}
					result.put("code", 200);
					result.put("message", "增加成功");
					
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					result.put("code",404);
					result.put("message", "增加失败");
					
				}
				
			}
			else{
				result.put("code", "404");
				result.put("message", "非法请求");
			}
			
			pw.print(JsonUtil.objectToJson(result));
			pw.flush();
			pw.close();
			
		}
		
		
}
