package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;
import org.ranji.lemon.volador.service.course.prototype.IChapterService;
import org.ranji.lemon.volador.service.course.prototype.IChapterTitleService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class chapterController {

	@Autowired
	private IChapterService chapterService;
	
	@Autowired
	private IChapterTitleService chapterTitleService;
	
	@Autowired
	private IAdminService adminService;
	
	@RequestMapping(value="/chapter",method=RequestMethod.GET)
	public void chapterList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int chapterTitleId=Integer.parseInt(request.getParameter("chapterTitleId"));
				
				List<Chapter> chapterList=chapterTitleService.findChapterByChapterTitle(chapterTitleId);
				result.put("code", 200);
				result.put("message", "获取成功");
				Map chapterMap=new HashMap();
				chapterMap.put("chapterList", chapterList);
				result.put("data", chapterMap);
				
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
	
	@RequestMapping(value="/chapter",method=RequestMethod.PUT)
	public void putchapterList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				Chapter chapter=new Chapter();
				chapter.setId(Integer.parseInt(request.getParameter("chapter_id")));
				chapter.setChapter_name(request.getParameter("chapter_name"));
				chapter.setVideo_address(request.getParameter("vedio_address"));
				chapter.setChapter_order(Integer.parseInt(request.getParameter("chapter_order")));
				chapter.setChapter_info(request.getParameter("chapter_info"));
				chapter.setTotal_time(request.getParameter("total_time"));
				chapter.setAccessory_name(request.getParameter("accessory_name"));
				chapter.setAccessory_download(request.getParameter("accessory_download"));
				
				
				chapterService.update(chapter);
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
	
	
	@RequestMapping(value="/chapter",method=RequestMethod.DELETE)
	public void deleteChapter(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				int chapterId=Integer.parseInt(request.getParameter("chapter_id"));
				
				chapterService.delete(chapterId);
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
	
	
	@RequestMapping(value="/chapter",method=RequestMethod.POST)
	public void addChapter(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				Chapter chapter=new Chapter();
				chapter.setChapter_name(request.getParameter("chapter_name"));
				chapter.setChapter_info(request.getParameter("chapter_info"));
				chapter.setChapter_title_id(Integer.parseInt(request.getParameter("chapter_title_id")));
				chapter.setVideo_address(request.getParameter("vedio_address"));
				chapter.setChapter_order(Integer.parseInt(request.getParameter("chapter_order")));
				chapter.setTotal_time(request.getParameter("total_time"));
				chapter.setAccessory_name(request.getParameter("accessory_name"));
				chapter.setAccessory_download(request.getParameter("accessory_download"));
				
				chapterService.save(chapter);
				result.put("code", 200);
				result.put("message", "增加成功");
				
			} catch (Exception e) {
				// TODO: handle exception
				result.put("code", "404");
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
