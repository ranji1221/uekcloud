package org.ranji.lemon.volador.controller.backstageManagement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Direction;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.service.course.prototype.IDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseDirectionController {

	@Autowired
	private IDirectionService directionService; 
	
	/**
	 * 查询所有课程方向信息
	 * @return
	 */
	@RequestMapping(value="/courseDirection", method=RequestMethod.GET)
	public String findAllCourseDerection(HttpServletRequest request){
		
		Map resultMap = new HashMap<>();
		try {
			List<Direction> directionList = directionService.findAll();
			resultMap.put("code", 200);
			resultMap.put("message", "获取成功");
			Map courseDirectionMap = new HashMap<>();
			courseDirectionMap.put("courseDirectionList", directionList);
			resultMap.put("data", courseDirectionMap);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resultMap.put("code", 404);
			resultMap.put("message", "获取失败");
		}
		System.out.println(JsonUtil.objectToJson(resultMap));
		return JsonUtil.objectToJson(resultMap);
	}
	
	/**
	 * 修改课程方向信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/courseDirection", method=RequestMethod.PUT)
	public String updateCourseDirection(HttpServletRequest request){
		Map result = new HashMap<>();
		try {
			int directionId =Integer.parseInt(request.getParameter("id"));
			String directionName = request.getParameter("name");
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
		System.out.println(JsonUtil.objectToJson(result));
		return JsonUtil.objectToJson(result);
		
	}
	
	/**
	 * 删除课程方向
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/courseDirection", method=RequestMethod.DELETE)
	public String deleteCourseDirection(HttpServletRequest request){
		Map result = new HashMap<>();
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
		System.out.println(JsonUtil.objectToJson(result));
		return JsonUtil.objectToJson(result);
		
	}
	
	/**
	 * 增加课程方向信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/courseDirection", method=RequestMethod.POST)
	public String saveCourseDirection(HttpServletRequest request){
		Map result = new HashMap<>();
		try {
		
			String directionName = request.getParameter("name");
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
		System.out.println(JsonUtil.objectToJson(result));
		return JsonUtil.objectToJson(result);
		
	}
}
