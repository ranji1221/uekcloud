package org.ranji.lemon.volador.service.growthclass.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.internal.runners.statements.ExpectException;
import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.growthclass.GrowthClass;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
import org.ranji.lemon.volador.persist.growthclass.prototype.IGrowthClassDao;
import org.ranji.lemon.volador.persist.growthclass.prototype.IGrowthStageDao;
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
				List<Chapter> chapterList = ((IGrowthClassDao) dao).findChapterByUserIdAndGrowthClassId(user_id, growthClass.getId());
				if(0 != chapterList.size()){
					Chapter chaper = chapterList.get(0);
					growthClassAndStageMap.put("studyChapter", chaper);
				}else{
					growthClassAndStageMap.put("studyChapter", null);
				}
				
				
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
					if(null != stageChapter){
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

	@Override
	public GrowthClass findGrowthClassByCourseId(int course_id) {
		
		return ((IGrowthClassDao) dao).findGrowthClassByCourseId(course_id).get(0);
	}

	@Override
	public Boolean saveGrowthClassOfChapterId(Integer user_id, Integer growthclass_id, Integer course_id, Integer chapterId) {
		Boolean bResult = true;
		
		try{
			
			if(null == user_id || null == growthclass_id){
				return false;
			}

			GrowthStage growthStage = new GrowthStage();
			if(null != course_id){
				growthStage = growthStageService.findGrowthStageByCourseId(course_id).get(0);
			}
				
			//用户第一次收藏职业导航
			List<Chapter> chapterList = ((IGrowthClassDao) dao).findChapterByUserIdAndGrowthClassId(user_id, growthclass_id);
			if(0 == chapterList.size() && null == chapterId){
				((IGrowthClassDao) dao).saveUserAndGrowthClassRelation(user_id, growthclass_id, 0);
			}else if(0 == chapterList.size() && null != chapterId){
				//如果用户已经在该职业导航中学习了章节，则更新章节
				((IGrowthClassDao) dao).saveUserAndGrowthClassRelation(user_id, growthclass_id, chapterId);
				//更新学习阶段与章节的关系表
				if(null != growthStage){
					growthStageService.saveUserStudyStage(user_id, growthclass_id, growthStage.getId(), chapterId);
				}else{
					growthStageService.saveUserStudyStage(user_id, growthclass_id, null, chapterId);
				}
				
				
			}else{
				//保存用户在职业导航课程中看的章节
				Chapter chaper = chapterList.get(0);
				
				if(null == chaper){
					//保存职业导航与学习章节关系表，以便在职业导航显示正在学习的章节
					((IGrowthClassDao) dao).saveUserAndGrowthClassRelation(user_id, growthclass_id, chapterId);
					
					//保存职业导航阶段与学习章节关系表，以便在职业导航阶段可以继续学习章节
					//查看学习的阶段是否是该职业导航中的
					//如果关系表不存在，则保存关系
					growthStageService.saveUserStudyStage(user_id, growthclass_id, growthStage.getId(), chapterId);
									
				}else{
					//如果用户已经在该职业导航中学习了章节，则更新章节
					((IGrowthClassDao) dao).updateUserAndGrowthClassRelation(user_id, growthclass_id, chapterId);
					//更新学习阶段与章节的关系表
					growthStageService.updateUserStudyStage(user_id, growthclass_id, growthStage.getId(), chapterId);
				}
			}
		}catch(Exception e){
			bResult = false;
		}
		return bResult;
	}
}
