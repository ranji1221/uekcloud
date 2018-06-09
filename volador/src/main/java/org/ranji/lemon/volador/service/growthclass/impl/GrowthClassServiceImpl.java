package org.ranji.lemon.volador.service.growthclass.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.growthclass.GrowthClass;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
import org.ranji.lemon.volador.persist.growthclass.prototype.IGrowthClassDao;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthClassService;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 成长体系service实现类
 * @author 范小亚
 * @date 2018/6/1
 * @since JDK1.8
 * @version 1.0
 */
@Service("VoladorGrowthClassServiceImpl")
public class GrowthClassServiceImpl extends GenericServiceImpl<GrowthClass, Integer> implements IGrowthClassService{

	@Autowired
	private IGrowthStageService growthStageService;
	@Override
	public void saveGrowthClassAndStageRelation(int class_id, int stage_id) {
		((IGrowthClassDao) dao).saveGrowthClassAndStageRelation(class_id, stage_id);		
	}

	@Override
	public void deleteGrowthClassAndStageRelation(int class_id, int stage_id) {
		((IGrowthClassDao) dao).deleteGrowthClassAndStageRelation(class_id, stage_id);
	}

	@Override
	public void deleteGrowthClassAndStageRelationByClassId(int class_id) {
		((IGrowthClassDao) dao).deleteGrowthClassAndStageRelationByClassId(class_id);
	}

	@Override
	public void updateGrowthClassAndStageRelation(int class_id, int stage_id) {
		((IGrowthClassDao) dao).updateGrowthClassAndStageRelation(class_id, stage_id);
	}

	@Override
	public List<GrowthStage> findGrowthStageByGrowthClassId(int class_id) {
		return ((IGrowthClassDao) dao).findGrowthStageByGrowthClassId(class_id);
	}

	@Override
	public GrowthClass findGrowthClassByGrowthStageId(int stage_id) {
		return ((IGrowthClassDao) dao).findGrowthClassByGrowthStageId(stage_id).get(0);
	}

	@Override
	public void saveUserAndGrowthClassRelation(int user_id, int growthclass_id) {
		((IGrowthClassDao) dao).saveUserAndGrowthClassRelation(user_id, growthclass_id);
	}

	@Override
	public List<GrowthClass> findGrowthClassByUserId(int user_id) {
		return ((IGrowthClassDao) dao).findGrowthClassByUserId(user_id);
	}

	@Override
	public List<Map> findUserCollectGrowth(Integer user_id) {
		List<Map> growthClassAndStageList = new ArrayList<Map>();
		if(null != user_id){
			//获取用户收藏的职业导航
			List<GrowthClass> growthClassList = findGrowthClassByUserId(user_id);
			//获取用户每个职业导航学习到的章节及阶段
			for(GrowthClass growthClass:growthClassList){
				Map<String, Object> growthClassAndStageMap = new HashMap<String, Object>();
				//根据用户ID和职业导航ID获取章节
				growthClassAndStageMap.put("growthClassId", growthClass.getId());
				growthClassAndStageMap.put("growthClassTitle", growthClass.getTitle());
				growthClassAndStageMap.put("growthClassSendword", growthClass.getSend_word());
				growthClassAndStageMap.put("growthClassDescription", growthClass.getDescription());
				growthClassAndStageMap.put("growthClassImage", growthClass.getImage());
				//获取用户该职业导航正在学习的课程
				   //获取用户正在该导航学习的章节
				Chapter chaper = ((IGrowthClassDao) dao).findChapterByUserIdAndGrowthClassId(user_id, growthClass.getId()).get(0);
				growthClassAndStageMap.put("studyChapter", chaper);
				
				List<Map> growthStageAndCourseList = new ArrayList<Map>();
				//获取该职业导航写的所有阶段
				List<GrowthStage> growthStageList = findGrowthStageByGrowthClassId(growthClass.getId());
				for(GrowthStage growthStage:growthStageList){
					Map<String, Object> growthStageAndCourseMap = new HashMap<String, Object>();
					growthStageAndCourseMap.put("growthStageTitle", growthStage.getTitle());
					growthStageAndCourseMap.put("growthStageDescription", growthStage.getDescription());
					growthStageAndCourseMap.put("growthStageNumber", growthStage.getNumber());
					//查询用户正在学习的课程是否是该阶段的
					Chapter stageChapter = growthStageService.findChapterByUserIdAndClassIdAndStageId(user_id, growthClass.getId(), growthStage.getId());
					//用户在职业导航学习的章节是否是该阶段的章节
					if(null != stageChapter && stageChapter.getId() == chaper.getId()){
						//添加正在学习
						growthStageAndCourseMap.put("studying", true);
					}else{
						growthStageAndCourseMap.put("studying", false);
					}
					//获取该阶段下的所有课程
					List<Course> courseList = growthStageService.findCourseByGrowthStageId(growthStage.getId());
					growthStageAndCourseMap.put("courseList", courseList);
					
					growthStageAndCourseList.add(growthStageAndCourseMap);
				}
				
				growthClassAndStageMap.put("growthStageAndCourseList", growthStageAndCourseList);
				
				growthClassAndStageList.add(growthClassAndStageMap);
				
			}
			
			return growthClassAndStageList;
		}else{
			return null;
		}
		
	}

}
