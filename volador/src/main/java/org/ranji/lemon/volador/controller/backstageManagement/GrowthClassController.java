package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.growthclass.GrowthClass;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
import org.ranji.lemon.volador.model.growthclass.LabelClassify;
import org.ranji.lemon.volador.model.growthclass.StageLabel;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthClassService;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthStageService;
import org.ranji.lemon.volador.service.growthclass.prototype.ILabelClassifyService;
import org.ranji.lemon.volador.service.growthclass.prototype.IStageLabelService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 职业导航内容管理系统Controller
 * @author 范小亚
 *
 */
@Controller
public class GrowthClassController {
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IGrowthClassService growthClassService;
	@Autowired
	private IGrowthStageService growthStageService;
	@Autowired
	private ILabelClassifyService labelClassifyService;
	@Autowired
	private IStageLabelService stageLabelService;
	@Autowired
	private ICourseService courseService;
	
	@RequestMapping(value="/labelClassify", method=RequestMethod.POST)
	public void addLabelClassify(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				//String growthClass_id = request.getParameter("growthClass_id");
				//String growthstage_id = request.getParameter("growthstage_id");
				String stageLabel_id = request.getParameter("stageLabel_id");
				
				//if(null != growthClass_id && null != growthstage_id && null != stageLabel_id){
				if(null != stageLabel_id){
					String classify = map.get("classify");
					if(null != classify){
						LabelClassify labelClassify = new LabelClassify();
						labelClassify.setClassify(classify);
						labelClassifyService.save(labelClassify);
						
						//保存对应关系
						//growthClassService.saveGrowthClassAndStageRelation(Integer.parseInt(growthClass_id), Integer.parseInt(growthstage_id));
						//stageLabelService.saveStageAndLabelRelation(Integer.parseInt(growthstage_id), Integer.parseInt(stageLabel_id));
						labelClassifyService.saveLabelAndClassifyRelation(Integer.parseInt(stageLabel_id), labelClassify.getId());
						
						result.put("code", 200);
						result.put("message", "增加成功");
					}else{
						result.put("code",404);
						result.put("message", "增加失败");
					}
					
					
				}else{
					result.put("code",404);
					result.put("message", "增加失败");
				}
				
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
	
	@RequestMapping(value="/labelClassify", method=RequestMethod.DELETE)
	public void deleteLabelClassify(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String labelClassify_id = request.getParameter("labelClassify_id");
				if(null != labelClassify_id){
					labelClassifyService.delete(Integer.parseInt(labelClassify_id));
					
					result.put("code", 200);
					result.put("message", "删除成功");
				}else{
					result.put("code",404);
					result.put("message", "删除失败");
				}
				
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
	
	@RequestMapping(value="/labelClassify",  method=RequestMethod.GET)
	public void listLabelClassify(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
		
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String labelClassify_id = request.getParameter("labelClassify_id");
				List<LabelClassify> labelClassifyList = new ArrayList<LabelClassify>();
				if(null != labelClassify_id){
					//查询指定标签
					labelClassifyList.add(labelClassifyService.find(Integer.parseInt(labelClassify_id)));
				}else{
					//查询全部标签
					labelClassifyList=labelClassifyService.findAll();
				}
				result.put("code", 200);
				result.put("message", "获取成功");
				result.put("data", labelClassifyList);
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
	
	@RequestMapping(value="/labelClassify", method=RequestMethod.PUT)
	public void updateLabelClassify(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				
				String labelClassify_id = request.getParameter("labelClassify_id");
				if(null != labelClassify_id){
					//查询指定标签
					LabelClassify labelClassify = labelClassifyService.find(Integer.parseInt(labelClassify_id));
					if(null != labelClassify){
						String classify = map.get("classify");
						labelClassify.setClassify(classify);
						labelClassifyService.update(labelClassify);
						
						result.put("code", 200);
						result.put("message", "修改成功");
					}else{
						result.put("code",404);
						result.put("message", "修改失败");
					}
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

	//职业导航阶段标签接口
	@RequestMapping(value="/stageLabel", method=RequestMethod.POST)
	public void addStageLabel(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String growthClass_id = map.get("growthClass_id");
				String growthstage_id = map.get("growthStage_id");
				
				
				
				if(null != growthClass_id && null != growthstage_id){
					String title = map.get("title");
					String label = map.get("label");
					
					StageLabel stageLabel = new StageLabel();
					stageLabel.setTitle(title);
					stageLabel.setLabel(label);
					
					stageLabelService.save(stageLabel);
					
					//保存对应关系
					//growthClassService.saveGrowthClassAndStageRelation(Integer.parseInt(growthClass_id), Integer.parseInt(growthstage_id));
					stageLabelService.saveStageAndLabelRelation(Integer.parseInt(growthstage_id), stageLabel.getId());
					result.put("code", 200);
					result.put("message", "增加成功");
					
				}else{
					result.put("code",404);
					result.put("message", "增加失败");
				}
				
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
	
	@RequestMapping(value="/stageLabel", method=RequestMethod.DELETE)
	public void deleteStageLabel(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String stageLabel_id = request.getParameter("stageLabel_id");
				if(null != stageLabel_id){
					stageLabelService.delete(Integer.parseInt(stageLabel_id));
					
					result.put("code", 200);
					result.put("message", "删除成功");
				}else{
					result.put("code",404);
					result.put("message", "删除失败");
				}
				
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
	
	@RequestMapping(value="/stageLabel",  method=RequestMethod.GET)
	public void listStageLabel(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
		
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String stageLabel_id = request.getParameter("stageLabel_id");
				List<Map> stageLabelList = new ArrayList<Map>();
				if(null == stageLabel_id){
					//查询全部
					stageLabelList = stageLabelService.listStageLabelAndClassify(null);
					
				}else{
					//查询指定
					stageLabelList = stageLabelService.listStageLabelAndClassify(Integer.valueOf(stageLabel_id));
				}
				result.put("code", 200);
				result.put("message", "获取成功");
				result.put("data", stageLabelList);
			
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
	
	@RequestMapping(value="/stageLabel", method=RequestMethod.PUT)
	public void updateStageLabel(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String stageLabel_id = request.getParameter("stageLabel_id");
				String title = map.get("title");
				String label = map.get("label");
				
				if(null != stageLabel_id){
					StageLabel stageLabel = stageLabelService.find(Integer.parseInt(stageLabel_id));
					if(null != stageLabel){
						if(null != title){
							stageLabel.setTitle(title);
						}
						if(null != label){
							stageLabel.setLabel(label);
						}
						
						//增加阶段标题
						stageLabelService.update(stageLabel);
						
						result.put("code", 200);
						result.put("message", "修改成功");
					}else{
						result.put("code",404);
						result.put("message", "修改失败");
					}
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
	
	//职业导航接口
	@RequestMapping(value="/growthClass", method=RequestMethod.POST)
	public void addGrowthClass(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String title = map.get("title");
				String send_word = map.get("send_word");
				String description = map.get("description");
				
				//增加职业导航
				GrowthClass growthClass = new GrowthClass();
				growthClass.setTitle(title);
				growthClass.setSend_word(send_word);
				growthClass.setDescription(description);
				growthClassService.save(growthClass);
				
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
	
	@RequestMapping(value="/growthClass", method=RequestMethod.DELETE)
	public void deleteGrowthClass(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String growthClass_id = request.getParameter("growthClass_id");
				if(null != growthClass_id){
					growthClassService.delete(Integer.parseInt(growthClass_id));
					
					result.put("code", 200);
					result.put("message", "删除成功");
				}else{
					result.put("code",404);
					result.put("message", "删除失败");
				}
				
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
	
	@RequestMapping(value="/growthClass",  method=RequestMethod.GET)
	public void listGrowthClass(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
		
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String growthClass_id = request.getParameter("growthClass_id");
				List<Map> growthClassList = new ArrayList<Map>();
				if(null == growthClass_id){
					//查询全部
					growthClassList =  growthClassService.listGrowthClassAndStage(null);
				}else{
					growthClassList =  growthClassService.listGrowthClassAndStage(Integer.valueOf(growthClass_id));
				}
				result.put("code", 200);
				result.put("message", "获取成功");
				result.put("data", growthClassList);
			
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
	
	@RequestMapping(value="/growthClass", method=RequestMethod.PUT)
	public void updateGrowthClass(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String growthclass_id = request.getParameter("growthClass_id");
				String title = map.get("title");
				String send_word = map.get("send_word");
				String description = map.get("description");
				
				if(null != growthclass_id){
					GrowthClass growthClass = growthClassService.find(Integer.parseInt(growthclass_id));
					if(null != growthClass){
						growthClass.setTitle(title);
						growthClass.setSend_word(send_word);
						growthClass.setDescription(description);
						growthClassService.update(growthClass);
						
						result.put("code", 200);
						result.put("message", "修改成功");
					}else{
						result.put("code",404);
						result.put("message", "修改失败");
					}
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
	
	//职业导航阶段接口
	@RequestMapping(value="/growthStage", method=RequestMethod.POST)
	public void addGrowthStage(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String growthclass_id = request.getParameter("growthClass_id");
				String title = map.get("title");
				String description = map.get("description");
				String image = map.get("image");
				String number = map.get("number");
				
				if(null != growthclass_id || null != number){
					GrowthStage growthStage = new GrowthStage();
					growthStage.setTitle(title);
					growthStage.setDescription(description);
					growthStage.setImage(image);
					growthStage.setNumber(Integer.parseInt(number));
					
					//保存阶段
					growthStageService.save(growthStage);
					
					//保存阶段与类的关系
					growthClassService.saveGrowthClassAndStageRelation(Integer.parseInt(growthclass_id), growthStage.getId());
					
					result.put("code", 200);
					result.put("message", "增加成功");
				}else{
					result.put("code",404);
					result.put("message", "增加失败");
				}
				
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
	
	@RequestMapping(value="/growthStage", method=RequestMethod.DELETE)
	public void deleteGrowthStage(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String growthstage_id = request.getParameter("growthstage_id");
				if(null != growthstage_id){
					growthStageService.delete(Integer.parseInt(growthstage_id));
					
					result.put("code", 200);
					result.put("message", "删除成功");
				}else{
					result.put("code",404);
					result.put("message", "删除失败");
				}
				
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
	
	@RequestMapping(value="/growthStage",  method=RequestMethod.GET)
	public void listGrowthStage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
		
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String growthstage_id = request.getParameter("growthstage_id");
				List<Map> growthStageList = new ArrayList<Map>();
				if(null == growthstage_id){
					//查询全部
					growthStageList =  growthStageService.listGrowthStageAndLebal(null);
				}else{
					growthStageList =  growthStageService.listGrowthStageAndLebal(Integer.valueOf(growthstage_id));
				}
				result.put("code", 200);
				result.put("message", "获取成功");
				result.put("data", growthStageList);
			
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
	
	@RequestMapping(value="/growthStage", method=RequestMethod.PUT)
	public void updateGrowthStage(HttpServletRequest request,HttpServletResponse response,@RequestBody Map<String,String> map) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String growthstage_id = request.getParameter("growthstage_id");
				String title = map.get("title");
				String description = map.get("description");
				String image = map.get("image");
				String number = map.get("number");
				
				if(null != growthstage_id){
					GrowthStage growthStage = growthStageService.find(Integer.parseInt(growthstage_id));
					if(null != growthStage){
						growthStage.setTitle(title);
						growthStage.setDescription(description);
						growthStage.setImage(image);
						growthStage.setNumber(Integer.parseInt(number));
						//保存阶段
						growthStageService.update(growthStage);
						
						result.put("code", 200);
						result.put("message", "修改成功");
					}else{
						result.put("code",404);
						result.put("message", "修改失败");
					}
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
	
	@RequestMapping(value="/findAllCourses",  method=RequestMethod.GET)
	public void listAllCourses(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
		
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				
				List<Course> courseList = courseService.findAll(null);
				result.put("code", 200);
				result.put("message", "获取成功");
				result.put("data", courseList);
			
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
	 * 职业导航阶段课程接口
	 */
	@RequestMapping(value="/growthStageCourse", method=RequestMethod.POST)
	public void addGrowthStageCourse(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Map<String, Object> result = new HashMap<>();
								
		if(adminService.parseJWT(request.getHeader("token"))){
			try {
				String growthstage_id = request.getParameter("growthstage_id");
				String course_id = request.getParameter("course_id");
				if(null != growthstage_id && null != course_id){
					int intGrowthStageId = Integer.parseInt(growthstage_id);
					int intCourseId = Integer.parseInt(course_id);
					
					//给该阶段添加课程
					growthStageService.saveGrowthStageAndCourseRelation(intGrowthStageId, intCourseId);
					Course course = courseService.find(intCourseId);
					GrowthStage growthStage = growthStageService.find(intGrowthStageId);
					//统计职业导航阶段课程时长，学生人数，课程价格
					growthStage.setTimeCount(courseService.findCourseTotalTime(intCourseId));
					growthStage.setStudentCount(course.getStudent_count());
					growthStage.setCoursePrice(course.getCourse_price());
					growthStageService.update(growthStage);
					
					result.put("code", 200);
					result.put("message", "增加成功");
				}else{
					result.put("code",404);
					result.put("message", "增加失败");
				}
				
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
		
		@RequestMapping(value="/growthStageCourse", method=RequestMethod.DELETE)
		public void deleteGrowthStageCourse(HttpServletRequest request, HttpServletResponse response) throws IOException{
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			Map<String, Object> result = new HashMap<>();
									
			if(adminService.parseJWT(request.getHeader("token"))){
				try {
					String growthstage_id = request.getParameter("growthstage_id");
					String course_id = request.getParameter("course_id");
					if(null != growthstage_id && null != course_id){
						growthStageService.deleteGrowthStageAndCourseRelation(Integer.parseInt(growthstage_id), Integer.parseInt(course_id));
						result.put("code", 200);
						result.put("message", "删除成功");
					}else{
						result.put("code",404);
						result.put("message", "删除失败");
					}
					
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
		
		@RequestMapping(value="/growthStageCourse",  method=RequestMethod.GET)
		public void listGrowthStageCourse(HttpServletRequest request,HttpServletResponse response) throws IOException{
			response.setHeader("Content-Type", "application/json;charset=utf-8");
			PrintWriter pw = response.getWriter();
			Map<String, Object> result = new HashMap<>();
			
			if(adminService.parseJWT(request.getHeader("token"))){
				try {
					String growthstage_id = request.getParameter("growthstage_id");
					List<Map> growthStageList = new ArrayList<Map>();
					if(null == growthstage_id){
						//查询全部
						growthStageList =  growthStageService.listGrowthStageAndLebal(null);
					}else{
						growthStageList =  growthStageService.listGrowthStageAndLebal(Integer.valueOf(growthstage_id));
					}
					result.put("code", 200);
					result.put("message", "获取成功");
					result.put("data", growthStageList);
				
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
}
