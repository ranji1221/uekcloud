package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.model.global.Notification;
import org.ranji.lemon.volador.service.global.prototype.INotificationService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NotificationController {
@Autowired
 private INotificationService notificationService;
 
@Autowired
private IAdminService adminService;
/**
 * 分页查询公告信息
 * @param request
 * @return
 * @throws IOException 
 */
	@RequestMapping(value="/notification", method=RequestMethod.GET)
	public void allTeacherInfoByPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			int page = 0;
			int limit = 0;
			try {
				if(request.getParameter("page")!=null){
					page = Integer.parseInt(request.getParameter("page").toString());
				}else{
					page = 1;
				}
				limit = Integer.parseInt(request.getParameter("limit").toString());
				Map notificationResult = notificationService.notificationListByPage(page, limit);
				result = notificationResult;
				result.put("code", 200);
				result.put("message", "获取成功");				
				
			} catch (Exception e) {
				// TODO: handle exception
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
//		return JsonUtil.objectToJson(result);
		
	}
	
	/**
	 * 修改公告的信息
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/notification", method=RequestMethod.PUT)
	public void updateTeacherInfo(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws Exception{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int notificationId =Integer.parseInt(request.getParameter("notification_id"));
				String notificationTitle = map.get("notification_title");
				String notificationContent = map.get("notification_content"); 
				int notificationType = Integer.parseInt(map.get("notification_type"));
				Notification notification = new Notification();
				notification.setId(notificationId);
				notification.setNotificationTitle(notificationTitle);
				notification.setNotificationContent(notificationContent);
				notification.setNotificationType(notificationType);
				notificationService.update(notification);
				result.put("code", 200);
				result.put("message", "修改成功");
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				result.put("code",404);
				result.put("message", "修改失败");	
			}
//			return JsonUtil.objectToJson(result);
		}else{
			result.put("code", "404");
			result.put("message", "非法请求");
		}
		pw.print(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();
		
	}
	
	/**
	 * 删除公告的信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/notification", method=RequestMethod.DELETE)
	public void deleteTeacherInfo(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int notificationId =Integer.parseInt(request.getParameter("notification_id"));
				notificationService.delete(notificationId);
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
	 * 增加公告信息
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/notification", method=RequestMethod.POST)
	public void addTeacherInfo(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String notificationTitle = map.get("notification_title");
				String notificationContent = map.get("notification_content"); 
				int notificationType = Integer.parseInt(map.get("notification_type"));
				Notification notification = new Notification();
				notification.setNotificationTitle(notificationTitle);
				notification.setNotificationContent(notificationContent);
				notification.setNotificationType(notificationType);
				notificationService.save(notification);
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
