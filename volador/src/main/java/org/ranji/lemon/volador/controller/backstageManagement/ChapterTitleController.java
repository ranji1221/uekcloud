package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.ChapterTitle;
import org.ranji.lemon.volador.service.course.prototype.IChapterTitleService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChapterTitleController {
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private IChapterTitleService chapterTitleService;
	
	@Autowired
	private ICourseService chourseService;
	
	@RequestMapping(value="chapterTitle",method=RequestMethod.GET)
	public void chapterTitleList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int courseId=Integer.parseInt(request.getParameter("course_id"));
				List<ChapterTitle> chapterTitleList=chourseService.findChapterTitleByCourse(courseId);
				result.put("code", 200);
				result.put("message", "获取成功");
				Map chapterTitleMap=new HashMap();
				chapterTitleMap.put("chapterTitleList", chapterTitleList);
				result.put("data", chapterTitleMap);
				
			} catch (Exception e) {
				// TODO: handle exception
				result.put("code", "404");
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
	
	
	@RequestMapping(value="chapterTitle",method=RequestMethod.PUT)
	public void chapterTitlePut(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int chapterTitleId=Integer.parseInt(request.getParameter("chapter_title_id"));
				String chapterTitle=map.get("chapter_title");
				int chapterTitleOrder=Integer.parseInt(map.get("chapter_title_order"));
				
				ChapterTitle chapterTitleSave=new ChapterTitle();
				chapterTitleSave.setId(chapterTitleId);
				chapterTitleSave.setChapter_title(chapterTitle);
				chapterTitleSave.setChapter_title_order(chapterTitleOrder);
				chapterTitleService.update(chapterTitleSave);
				
				result.put("code", 200);
				result.put("message", "修改成功");
				
			} catch (Exception e) {
				// TODO: handle exception
				result.put("code", "404");
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
	
	@RequestMapping(value="chapterTitle",method=RequestMethod.DELETE)
	public void deleteChapterTitle(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int chapterTitleId=Integer.parseInt(request.getParameter("chapter_title_id"));
				
				chapterTitleService.delete(chapterTitleId);
				
				result.put("code", 200);
				result.put("message", "删除成功");
				
			} catch (Exception e) {
				// TODO: handle exception
				result.put("code", "404");
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
	
	
	@RequestMapping(value="chapterTitle",method=RequestMethod.POST)
	public void addChapterTitle(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int courseId=Integer.parseInt(request.getParameter("course_id"));
				String chapterTitle=map.get("chapter_title");
				int chapterTitleOrder=Integer.parseInt(map.get("chapter_title_order"));
				
				ChapterTitle chapterTitleSave=new ChapterTitle();
				chapterTitleSave.setCourse_id(courseId);
				chapterTitleSave.setChapter_title(chapterTitle);
				chapterTitleSave.setChapter_title_order(chapterTitleOrder);
				
				chapterTitleService.save(chapterTitleSave);
				result.put("code", 200);
				result.put("message", "添加成功");
				
			} catch (Exception e) {
				// TODO: handle exception
				result.put("code", "404");
				result.put("message", "添加失败");
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
