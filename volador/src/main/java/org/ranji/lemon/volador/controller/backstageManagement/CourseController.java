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
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Direction;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("AdminCourseController")
public class CourseController {
	@Autowired
	private IClassifyService classifyService;
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IAdminService adminService;
	 /**
	  * 根据分类id查询课程
	  * @param request
	  * @return
	 * @throws IOException 
	  */
	@RequestMapping(value="/course",method=RequestMethod.GET)
	public void findCourse(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int classifyId = Integer.parseInt(request.getParameter("classify_id"));
				List<Course> courseList = classifyService.findCourseByClassify(classifyId);
				result.put("code", 200);
				result.put("message", "获取成功");
				Map courseMap = new HashMap<>();
				courseMap.put("courseList", courseList);
				result.put("data", courseMap);
				
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
	 * 修改课程信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/course",method=RequestMethod.PUT)
	public void updateCourseInfo(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int courseId =Integer.parseInt(request.getParameter("course_id"));
				String courseName = map.get("course_name");
				String courseInfo = map.get("course_info");
				double price =Double.parseDouble(map.get("price"));
				String courseImageAddress = map.get("course_image_address");
				Course course = new Course();
				course.setId(courseId);
				course.setCourse_name(courseName);
				course.setCourse_info(courseInfo);
				course.setCourse_price(price);
				course.setCourse_image_address(courseImageAddress);
				courseService.update(course);
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
	 * 删除课程信息以及课程和分类之间关系
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/course",method=RequestMethod.DELETE)
	public void deleteCourse(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int classifyId =Integer.parseInt(request.getParameter("classify_id"));
				int courseId =Integer.parseInt(request.getParameter("course_id"));
				classifyService.deleteClassifyAndCourseRelation(courseId, classifyId);
				courseService.delete(courseId);
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
	 * 增加课程信息，包括课程信息以及课程和分类的关系、课程和老师的关系
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/course",method=RequestMethod.POST)
	public void saveCourseInfo(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String courseName = map.get("course_name");
				String courseInfo = map.get("course_info");
				double price =Double.parseDouble(map.get("price"));
				String courseImageAddress = map.get("course_image_address");
				int classifyId =Integer.parseInt(request.getParameter("classify_id"));
				int teacherId =Integer.parseInt(map.get("teacher_id"));
				Course course = new Course();
				course.setCourse_name(courseName);
				course.setCourse_info(courseInfo);
				course.setCourse_price(price);
				course.setCourse_image_address(courseImageAddress);
				courseService.save(course);
				int courseId = course.getId();
				classifyService.saveClassifyAndCourseRelation(courseId, classifyId);
				courseService.saveCourseAndTeacherRelation(courseId, teacherId);
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
