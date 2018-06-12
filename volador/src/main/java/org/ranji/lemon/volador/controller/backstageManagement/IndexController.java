package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.util.DateUtil;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Carouse;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.model.course.Theme;
import org.ranji.lemon.volador.model.personal.Student;
import org.ranji.lemon.volador.service.course.prototype.ICarouseService;
import org.ranji.lemon.volador.service.course.prototype.ITeacherService;
import org.ranji.lemon.volador.service.course.prototype.IThemeService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.ranji.lemon.volador.service.personal.prototype.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class IndexController {
	@Autowired
	private IThemeService themeService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private ICarouseService carouseService;
	@Autowired
	private ITeacherService teacherService;
	@Autowired
	private IStudentService studentService;
	/**
	 * 增加首页推荐主题及其课程
	 * @param request		
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/themeRecommend", method=RequestMethod.POST)
	public void addIndexTheme(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				//推薦主題，描述及課程id都為必選字段
				String title = map.get("title");
				String description = map.get("description");
				String courseId = map.get("courseId");
				
				Theme theme = new Theme();
				//設置為輸入的主題及描述
				theme.setTitle(title);
				theme.setDescribe(description);
				themeService.save(theme);
				
				//保存主題及其課程的關係表
				themeService.saveCourseAndThemeRelation(theme.getId(), Integer.parseInt(courseId));
				
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
	/**
	 * 删除首页推荐分类及课程
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/themeRecommend", method=RequestMethod.DELETE)
	public void deleteIndexTheme(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				//首页推荐主题ID及课程ID
				Integer themeId = Integer.parseInt(request.getParameter("themeId"));
				Integer courseId = Integer.parseInt(request.getParameter("courseId"));
				
				if(themeService.deleteCourseAndThemeRelation(themeId, courseId)){
					result.put("code", 200);
					result.put("message", "删除成功");
				}else{
					result.put("code",404);
					result.put("message", "删除失败");
				}
				
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
	 * 查询全部或指定分类下的课程
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/themeRecommend", method=RequestMethod.GET)
	public void listIndexTheme(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
		//List<>
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				//根据首页推荐主题ID查找课程
				String strThemeId = request.getParameter("themeId");
				Integer themeId = null;
				if(null != strThemeId){
					themeId = Integer.parseInt(strThemeId);
				}
				
				//根据分类ID查找分类及课程
				Map<String, Object> themeAndcourseList = themeService.findCourseByThemeId(themeId);								
				result.put("code", 200);
				result.put("message", "获取成功");
				result.put("data", themeAndcourseList);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("code",404);
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
	 * 增加首页轮播
	 * @param request		
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/carousel", method=RequestMethod.POST)
	public void addCarousel(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String title = map.get("title");
				String description =  map.get("description");
				String chainedAddress =  map.get("chainedAddress");
				String image =  map.get("image");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Long showTimeLong =  Long.parseLong(map.get("showTime"));
				Date showTime = new Date(showTimeLong);

				Carouse carouse = new Carouse();
				carouse.setTitle(title);
				carouse.setDescription(description);
				carouse.setChainedAddress(chainedAddress);
				carouse.setShowTime(showTime);
				carouse.setImage(image);
				carouseService.save(carouse);
				
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
	/**
	 * 删除首页轮播
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/carousel", method=RequestMethod.DELETE)
	public void deleteCarousel(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				//首页推荐主题ID及课程ID
				Integer carouselId = Integer.parseInt(request.getParameter("carouselId"));
				
				carouseService.delete(carouselId);
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
	 * 查询首页轮播
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/carousel", method=RequestMethod.GET)
	public void listCarousel(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				//首页推荐主题ID及课程ID
				String strCarouselId = request.getParameter("carouselId");
				List<Carouse> carouseList = new ArrayList<Carouse>();
				Integer carouselId = null;
				if(null == strCarouselId){
					carouseList=carouseService.findAll();
				}else{
					carouselId = Integer.parseInt(strCarouselId);
					carouseList.add(carouseService.find(carouselId));
				}
								
				result.put("code", 200);
				result.put("message", "获取成功");
				result.put("data", carouseList);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("code",404);
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
	 * 修改首页轮播信息
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/carousel", method=RequestMethod.PUT)
	public void updateCarousel(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws Exception{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String strCarouselId = request.getParameter("carouselId");
				Integer carouselId = Integer.parseInt(request.getParameter("carouselId"));
				String title = map.get("title");
				String description = map.get("description");
				String chainedAddress = map.get("chainedAddress");
				String image = map.get("image");
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				Long showTimeLong =  Long.parseLong(map.get("showTime"));
				Date showTime = new Date(showTimeLong);
				
				//获取需要修改的轮播数据
				Carouse carouse = carouseService.find(carouselId);
				if(null != carouse){
					carouse.setTitle(title);
					carouse.setDescription(description);
					carouse.setChainedAddress(chainedAddress);
					carouse.setShowTime(showTime);
					carouse.setImage(image);
					carouseService.update(carouse);
					result.put("code", 200);
					result.put("message", "修改成功");
				}else
				{
					result.put("code",404);
					result.put("message", "修改失败");
				}
				
				
			} catch (Exception e) {
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
	 * 增加首页推荐老师
	 * @param request		
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/representativeTeacher", method=RequestMethod.POST)
	public void addRepresentativeTeacher(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				Integer teacherId = Integer.parseInt(map.get("teacherId"));
				String type = map.get("type");
				String company = map.get("company");
				String label = map.get("label");
				String link = map.get("link");
				
				Teacher teacher = teacherService.find(teacherId);
				if(null != teacher){
					teacher.setType(type);
					teacher.setCompany(company);
					teacher.setLabel(label);
					teacher.setLink(link);
					teacherService.update(teacher);
					//保存首页推荐老师
					teacherService.saveHeaderTeacher(teacherId);
				}
							
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
	/**
	 * 删除首页推荐老师
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/representativeTeacher", method=RequestMethod.DELETE)
	public void deleteRepresentativeTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				Integer teacherId = Integer.parseInt(request.getParameter("teacherId"));
				teacherService.deleteHeaderTeacher(teacherId);
				
				result.put("code", 200);
				result.put("message", "删除成功");
			} catch (Exception e) {
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
	 * 查询首页推荐老师
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/representativeTeacher", method=RequestMethod.GET)
	public void listRepresentativeTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				List<Teacher> teacherList = teacherService.findHeaderTeacher();
				
				result.put("code", 200);
				result.put("message", "获取成功");
				result.put("data", teacherList);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("code",404);
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
	 * 修改首页推荐老师信息
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/representativeTeacher", method=RequestMethod.PUT)
	public void updateRepresentativeTeacher(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws Exception{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				Integer teacherId = Integer.parseInt(request.getParameter("teacherId"));
				String type = map.get("type");
				String company = map.get("company");
				String label = map.get("label");
				
				Teacher teacher = teacherService.find(teacherId);
				if(null != teacher){
					teacher.setType(type);
					teacher.setCompany(company);
					teacher.setLabel(label);
					teacherService.update(teacher);
					
					result.put("code", 200);
					result.put("message", "修改成功");
				}else{
					result.put("code",404);
					result.put("message", "修改失败");
				}
				
			} catch (Exception e) {
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
	 * 增加优秀学员
	 * @param request		
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/excellentStudent", method=RequestMethod.POST)
	public void addExcellentStudent(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String name = map.get("name");
				String company = map.get("company");
				String position = map.get("position");
				String title = map.get("title");
				String description = map.get("description");
				String image = map.get("image");
				
				Student student = new Student();
				student.setName(name);
				student.setCompany(company);
				student.setPosition(position);
				student.setTitle(title);
				student.setDescription(description);
				student.setImage(image);
				studentService.save(student);
				
				result.put("code", 200);
				result.put("message", "增加成功");
				
			} catch (Exception e) {
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
	/**
	 * 删除优秀学员
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/excellentStudent", method=RequestMethod.DELETE)
	public void deleteExcellentStudent(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				Integer studentId = Integer.parseInt(request.getParameter("studentId"));
				
				studentService.delete(studentId);
				
				result.put("code", 200);
				result.put("message", "删除成功");
			} catch (Exception e) {
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
	 * 查询优秀学员
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/excellentStudent", method=RequestMethod.GET)
	public void listExcellentStudent(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				Integer studentId = Integer.parseInt(request.getParameter("studentId"));
				List<Student> studentList = new ArrayList<>();
				if(null == studentId){
					studentList = studentService.findAll();
				}else{
					studentList.add(studentService.find(studentId));
				}
												
				result.put("code", 200);
				result.put("message", "获取成功");
				result.put("data", studentList);
			} catch (Exception e) {
				e.printStackTrace();
				result.put("code",404);
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
	 * 修改优秀学员信息
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/excellentStudent", method=RequestMethod.PUT)
	public void updateExcellentStudent(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws Exception{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				Integer studentId = Integer.parseInt(request.getParameter("studentId"));
				String name = map.get("name");
				String company = map.get("company");
				String position = map.get("position");
				String title = map.get("title");
				String description = map.get("description");
				String image = map.get("image");
				
				//查询需要修改的学生信息
				Student student = studentService.find(studentId);
				if(null != name) student.setName(name);
				if(null != company) student.setCompany(company);
				if(null != position) student.setPosition(position);
				if(null != title) student.setTitle(title);
				if(null != description) student.setDescription(description);
				if(null != image) student.setImage(image);
				studentService.update(student);
				
				result.put("code", 200);
				result.put("message", "修改成功");
				
			} catch (Exception e) {
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
}
