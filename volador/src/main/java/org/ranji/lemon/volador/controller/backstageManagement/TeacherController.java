package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.service.course.prototype.ITeacherService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
public class TeacherController {
	
	@Autowired
	private ITeacherService teacherService;
	
	@Autowired
	private IAdminService adminService;
	/**
	 * 分页查询教师信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
		@RequestMapping(value="/teacher", method=RequestMethod.GET)
		public void allTeacherInfoByPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			Map result = new HashMap<>();
			if(!adminService.parseJWT(request.getHeader("token"))){
				result.put("code", "404");
				result.put("message", "非法请求");
//				return JsonUtil.objectToJson(result);
			}else{
				int page = 0;
				int limit = 0;
				try {
					if(request.getParameter("page")!=null){
						page = Integer.parseInt(request.getParameter("page").toString());
					}else{
						page = 1;
					}
					limit = Integer.parseInt(request.getParameter("limit").toString());
					PagerModel<Teacher> teacherInfo = teacherService.findTeacherInfoByPage(page, limit);
					result.put("code", 200);
					result.put("message", "获取成功");
					Map resultpage = new HashMap<>();
					int pageCount = 0;
					if(teacherInfo.getTotal()%limit==0){
						pageCount = teacherInfo.getTotal()/limit;
					}else{
						pageCount = teacherInfo.getTotal()/limit+1;
					}
					resultpage.put("pageCount", pageCount);
					
					resultpage.put("pageNo", page);
					resultpage.put("totalCount", teacherInfo.getTotal());
					result.put("page", resultpage);
					Map teacherMap = new HashMap<>();
					teacherMap.put("teachers", teacherInfo.getData());
					result.put("data", teacherMap);
					
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					result.put("code",404);
					result.put("message", "获取失败");
					
				}
			}
			System.out.println(JsonUtil.objectToJson(result));
			pw.print(JsonUtil.objectToJson(result));
			pw.flush();
			pw.close();
//			return JsonUtil.objectToJson(result);
			
		}
		
		/**
		 * 查询所有教师的信息
		 * @param request
		 * @return
		 * @throws IOException 
		 */
		@RequestMapping(value="/teacherList", method=RequestMethod.GET)
		public void findTeacherInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			Map result = new HashMap<>();
			if(!adminService.parseJWT(request.getHeader("token"))){
				result.put("code", "404");
				result.put("message", "非法请求");
//				return JsonUtil.objectToJson(result);
			}else{
				try {
					
					List<Teacher> teacherInfo = teacherService.findAll();
					result.put("code", 200);
					result.put("message", "获取成功");
					result.put("data", teacherInfo);
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					result.put("code",404);
					result.put("message", "获取失败");
					
				}
			}
			pw.print(JsonUtil.objectToJson(result));
			pw.flush();
			pw.close();
			
//			return JsonUtil.objectToJson(result);
			
		}
		
		/**
		 * 修改教师的信息
		 * @param request
		 * @return
		 * @throws Exception 
		 */
		@RequestMapping(value="/teacher", method=RequestMethod.PUT)
		public void updateTeacherInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			Map result = new HashMap<>();
			if(adminService.parseJWT(request.getHeader("token"))){
				try {
					int teacherId =Integer.parseInt(request.getParameter("teacher_id"));
					String teacherName = request.getParameter("teacher_name");
					String teacherInfo = request.getParameter("teacher_info"); 
					String teacherPosition = request.getParameter("teacher_position");
					String teacherImage = request.getParameter("teacher_image");
					Teacher teacher = new Teacher();
					teacher.setId(teacherId);
					teacher.setTeacher_name(teacherName);
					teacher.setTeacher_info(teacherInfo);
					teacher.setTeacher_position(teacherPosition);
					teacher.setTeacher_image(teacherImage);
				    teacherService.update(teacher);
					result.put("code", 200);
					result.put("message", "修改成功");
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					result.put("code",404);
					result.put("message", "修改失败");	
				}
//				return JsonUtil.objectToJson(result);
			}else{
				result.put("code", "404");
				result.put("message", "非法请求");
			}
			pw.print(JsonUtil.objectToJson(result));
			pw.flush();
			pw.close();
			
		}
		
		/**
		 * 删除教师的信息
		 * @param request
		 * @return
		 * @throws IOException 
		 */
		@RequestMapping(value="/teacher", method=RequestMethod.DELETE)
		public void deleteTeacherInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			Map result = new HashMap<>();
			if(adminService.parseJWT(request.getHeader("token"))){
				try {
					int teacherId =Integer.parseInt(request.getParameter("id"));
				    teacherService.delete(teacherId);
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
		 * 增加教师信息
		 * @param request
		 * @return
		 * @throws IOException 
		 */
		@RequestMapping(value="/teacher", method=RequestMethod.POST)
		public void addTeacherInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			Map result = new HashMap<>();
			if(adminService.parseJWT(request.getHeader("token"))){
				try {
					String teacherName = request.getParameter("teacher_name");
					String teacherInfo = request.getParameter("teacher_info"); 
					String teacherPosition = request.getParameter("teacher_position");
					String teacherImage = request.getParameter("teacher_image");
					Teacher teacher = new Teacher();
					teacher.setTeacher_name(teacherName);
					teacher.setTeacher_info(teacherInfo);
					teacher.setTeacher_position(teacherPosition);
					teacher.setTeacher_image(teacherImage);
				    teacherService.save(teacher);
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
