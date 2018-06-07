//package org.ranji.lemon.volador.controller.backstageManagement;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.ranji.lemon.core.util.JsonUtil;
//import org.ranji.lemon.volador.model.course.Classify;
//import org.ranji.lemon.volador.model.course.Course;
//import org.ranji.lemon.volador.model.course.Direction;
//import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
//import org.ranji.lemon.volador.service.course.prototype.ICourseService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//@Controller
//public class CourseController {
//	@Autowired
//	private IClassifyService classifyService;
//	
//	@Autowired
//	private ICourseService courseService;
//	 /**
//	  * 根据分类id查询课程
//	  * @param request
//	  * @return
//	  */
//	@RequestMapping(value="/course",method=RequestMethod.GET)
//	public String findCourse(HttpServletRequest request)
//	{
//		Map resultMap = new HashMap<>();
//		try {
//			int classifyId = Integer.parseInt(request.getParameter("classify_id"));
//			List<Course> courseList = classifyService.findCourseByClassify(classifyId);
//			resultMap.put("code", 200);
//			resultMap.put("message", "获取成功");
//			Map courseMap = new HashMap<>();
//			courseMap.put("courseList", courseList);
//			resultMap.put("data", courseMap);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			resultMap.put("code", 404);
//			resultMap.put("message", "获取失败");
//		}
//		System.out.println(JsonUtil.objectToJson(resultMap));
//		return JsonUtil.objectToJson(resultMap);
//	}
//
//	/**
//	 * 修改课程信息
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value="/course",method=RequestMethod.PUT)
//	public String updateCourseInfo(HttpServletRequest request){
//		Map result = new HashMap<>();
//		try {
//			int courseId =Integer.parseInt(request.getParameter("course_id"));
//			String courseName = request.getParameter("course_name");
//			String courseInfo = request.getParameter("course_info");
//			int price =Integer.parseInt(request.getParameter("price"));
//			String courseImageAddress = request.getParameter("course_image_address");
//			Course course = new Course();
//			course.setId(courseId);
//			course.setCourse_name(courseName);
//			course.setCourse_info(courseInfo);
//			course.setCourse_price(price);
//			course.setCourse_image_address(courseImageAddress);
//			courseService.update(course);
//			result.put("code", 200);
//			result.put("message", "修改成功");
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			result.put("code",404);
//			result.put("message", "修改失败");
//			
//		}
//		System.out.println(JsonUtil.objectToJson(result));
//		return JsonUtil.objectToJson(result);
//	}
//	
//	/**
//	 * 删除课程信息以及课程和分类之间关系
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value="/course",method=RequestMethod.DELETE)
//	public String deleteCourse(HttpServletRequest request){
//		Map result = new HashMap<>();
//		try {
//			int classifyId =Integer.parseInt(request.getParameter("classify_id"));
//			int courseId =Integer.parseInt(request.getParameter("course_id"));
//			classifyService.deleteClassifyAndCourseRelation(courseId, classifyId);
//			courseService.delete(courseId);
//			result.put("code", 200);
//			result.put("message", "删除成功");
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			result.put("code",404);
//			result.put("message", "删除失败");
//			
//		}
//		System.out.println(JsonUtil.objectToJson(result));
//		return JsonUtil.objectToJson(result);
//	}
//	
//	/**
//	 * 增加课程信息，包括课程信息以及课程和分类的关系、课程和老师的关系
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping(value="/course",method=RequestMethod.POST)
//	public String saveCourseInfo(HttpServletRequest request){
//		Map result = new HashMap<>();
//		try {
//			String courseName = request.getParameter("course_name");
//			String courseInfo = request.getParameter("course_info");
//			int price =Integer.parseInt(request.getParameter("price"));
//			String courseImageAddress = request.getParameter("course_image_address");
//			int classifyId =Integer.parseInt(request.getParameter("classify_id"));
//			int teacherId =Integer.parseInt(request.getParameter("teacher_id"));
//			Course course = new Course();
//			course.setCourse_name(courseName);
//			course.setCourse_info(courseInfo);
//			course.setCourse_price(price);
//			course.setCourse_image_address(courseImageAddress);
//			courseService.save(course);
//			int courseId = course.getId();
//			classifyService.saveClassifyAndCourseRelation(courseId, classifyId);
//			courseService.saveCourseAndTeacherRelation(courseId, teacherId);
//			result.put("code", 200);
//			result.put("message", "修改成功");
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			result.put("code",404);
//			result.put("message", "修改失败");
//			
//		}
//		System.out.println(JsonUtil.objectToJson(result));
//		return JsonUtil.objectToJson(result);
//		
//	}
//	
//}
