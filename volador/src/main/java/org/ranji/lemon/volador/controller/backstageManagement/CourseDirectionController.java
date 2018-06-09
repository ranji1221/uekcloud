package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Direction;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.service.course.prototype.IDirectionService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseDirectionController {

	@Autowired
	private IDirectionService directionService; 
	
	@Autowired
	private IAdminService adminService;
	
	/**
	 * 查询所有课程方向信息
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/courseDirection", method=RequestMethod.GET)
	public void findAllCourseDerection(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				List<Direction> directionList = directionService.findAll();
				result.put("code", 200);
				result.put("message", "获取成功");
				Map courseDirectionMap = new HashMap<>();
				courseDirectionMap.put("courseDirectionList", directionList);
				result.put("data", courseDirectionMap);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.put("code", 404);
				result.put("message", "获取失败");
			}
		}else{
			result.put("code", "404");
			result.put("message", "非法请求");
				
		}
		pw.print(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();
	}
	
	/**
	 * 
	 * 修改课程方向信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/courseDirection", method=RequestMethod.PUT)
	public void updateCourseDirection(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException {
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int directionId =Integer.parseInt(map.get("id"));
				String directionName = map.get("name");
				Direction courseDirection = new Direction();
				courseDirection.setId(directionId);
				courseDirection.setName(directionName);
				directionService.update(courseDirection);
				result.put("code", 200);
				result.put("message", "修改成功");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.put("code",404);
				result.put("message", "修改失败");
				
			}
		}else{
			result.put("code", "404");
			result.put("message", "非法请求");
				
		}
		pw.print(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();

		
	}
	
	/**
	 * 删除课程方向
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/courseDirection", method=RequestMethod.DELETE)
	public void deleteCourseDirection(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int directionId =Integer.parseInt(request.getParameter("id"));
				directionService.delete(directionId);
				result.put("code", 200);
				result.put("message", "删除成功");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.put("code",404);
				result.put("message", "删除失败");
				
			}
		}else{
			result.put("code", "404");
			result.put("message", "非法请求");
				
		}
		pw.print(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();
	}
	
	/**
	 * 增加课程方向信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/courseDirection", method=RequestMethod.POST)
	public void saveCourseDirection(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				
				String directionName = map.get("name");
				Direction courseDirection = new Direction();
				courseDirection.setName(directionName);
				directionService.save(courseDirection);
				result.put("code", 200);
				result.put("message", "增加成功");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.put("code",404);
				result.put("message", "增加失败");
				
			}
		}else{
			result.put("code", "404");
			result.put("message", "非法请求");
				
		}
		pw.print(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();
		
	}
}
