package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Direction;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.IDirectionService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseClassifyController {
	@Autowired
	private IClassifyService classifyService;
	
	@Autowired 
	private IDirectionService directionService;
	
	@Autowired
	private IAdminService adminService;
	
	/**
	 * 根据课程方向id查询课程分类信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/classify",method=RequestMethod.GET)
	public void findCourseClassifyInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int directionId = Integer.parseInt(request.getParameter("direction_id")); 
				List<Classify> classifyList = directionService.findClassifyByDirectionId(directionId);
				result.put("code", 200);
				result.put("message", "获取成功");
				Map classifyMap = new HashMap<>();
				classifyMap.put("classifyList", classifyList);
				result.put("data", classifyMap);
				
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
	 * 修改课程分类信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/classify",method=RequestMethod.PUT)
	public void updateClassify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int classifyId =Integer.parseInt(request.getParameter("classify_id"));
				String classifyName = request.getParameter("name");
				Classify classify = new Classify();
				classify.setId(classifyId);
				classify.setClassify_name(classifyName);
				classifyService.update(classify);
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
	 * 删除课程分类以及课程方向分类关系
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/classify", method=RequestMethod.DELETE)
	public void deleteClassify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int classifyId =Integer.parseInt(request.getParameter("classify_id"));
				int directionId =Integer.parseInt(request.getParameter("direction_id"));
				directionService.deleteDirectionAndClassifyRelation(directionId, classifyId);
				classifyService.delete(classifyId);
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
	 * 增加课程分类以及课程分类和课程方向关系
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/classify", method=RequestMethod.POST)
	public void  saveClassifyInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String classifyName = request.getParameter("name");
				int directionId =Integer.parseInt(request.getParameter("direction_id"));
				Classify classify = new Classify();
				classify.setClassify_name(classifyName);
				classifyService.save(classify);
				int classifyId = classify.getId();
				directionService.saveDirectionAndClassifyRelation(directionId, classifyId);
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
