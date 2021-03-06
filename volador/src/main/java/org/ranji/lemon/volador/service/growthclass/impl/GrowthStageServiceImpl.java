package org.ranji.lemon.volador.service.growthclass.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
import org.ranji.lemon.volador.model.growthclass.StageLabel;
import org.ranji.lemon.volador.persist.growthclass.prototype.IGrowthStageDao;
import org.ranji.lemon.volador.service.course.prototype.IChapterService;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthStageService;
import org.ranji.lemon.volador.service.growthclass.prototype.IStageLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 成长阶段service实现类
 * @author 范小亚
 * @date 2018/6/1
 * @since JDK1.8
 * @version 1.0
 */
@Service("VoladorGrowthStageServiceImpl")
public class GrowthStageServiceImpl extends GenericServiceImpl<GrowthStage, Integer> implements IGrowthStageService{

	@Autowired
	private IStageLabelService stageLabelService;
	@Autowired
	private IChapterService chapterService;
	@Override
	public void saveGrowthStageAndCourseRelation(int stage_id, int course_id) {
		((IGrowthStageDao) dao).saveGrowthStageAndCourseRelation(stage_id, course_id);		
	}

	@Override
	public void deleteGrowthStageAndCourseRelation(int stage_id, int course_id) {
		((IGrowthStageDao) dao).deleteGrowthStageAndCourseRelation(stage_id, course_id);
	}

	@Override
	public void deleteGrowthClassAndStageRelationByClassId(int stage_id) {
		((IGrowthStageDao) dao).deleteGrowthClassAndStageRelationByStageId(stage_id);
	}

	@Override
	public void deleteGrowthStageAndCourseRelationByStageId(int stage_id) {
		((IGrowthStageDao) dao).deleteGrowthStageAndCourseRelationByStageId(stage_id);
	}

	@Override
	public void updateGrowthStageAndCourseRelation(int stage_id, int course_id) {
		((IGrowthStageDao) dao).updateGrowthStageAndCourseRelation(stage_id, course_id);		
	}

	@Override
	public List<Course> findCourseByGrowthStageId(int stage_id) {
		return ((IGrowthStageDao) dao).findCourseByGrowthStageId(stage_id);
	}

	@Override
	public void save(GrowthStage entity) {
		//保存課程階段時獲取階段中課程價格，學生人數，課程時長并保存
		List<Course> courseList = findCourseByGrowthStageId(entity.getId());
		int timeCount = 0;
		int studentCount = 0;
		int coursePrice = 0;
		
		for(Course course:courseList){
			//timeCount += 
			studentCount += course.getStudent_count();
			coursePrice += course.getCourse_price();
		}
		entity.setCoursePrice(coursePrice);
		entity.setTimeCount(timeCount);
		entity.setStudentCount(studentCount);
		
		super.save(entity);
	}

	@Override
	public void update(GrowthStage entity) {
		//更新課程階段時獲取階段中課程價格，學生人數，課程時長并保存
		List<Course> courseList = findCourseByGrowthStageId(entity.getId());
		int timeCount = 0;
		int studentCount = 0;
		int coursePrice = 0;
		
		for(Course course:courseList){
			//timeCount += 
			studentCount += course.getStudent_count();
			coursePrice += course.getCourse_price();
		}
		entity.setCoursePrice(coursePrice);
		entity.setTimeCount(timeCount);
		entity.setStudentCount(studentCount);
		super.update(entity);
	}

	@Override
	public void saveUserStudyStage(Integer user_id, Integer growthclass_id, Integer growthstage_id, Integer chapter_id) {
		((IGrowthStageDao) dao).saveUserStudyStage(user_id, growthstage_id, chapter_id, chapter_id);
	}

	@Override
	public Chapter findChapterByUserIdAndClassIdAndStageId(int user_id, int growthclass_id, int growthstage_id) {	
		try{
			List<Integer> chapterIdList = ((IGrowthStageDao) dao).findChapterByUserIdAndClassIdAndStageId(user_id, growthclass_id, growthstage_id);
			if(0 != chapterIdList.size()){
				return chapterService.find(chapterIdList.get(0));
			}else{
				return null;
			}
			 
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public List<GrowthStage> findGrowthStageByCourseId(int course_id) {
		return ((IGrowthStageDao) dao).findGrowthStageByCourseId(course_id);
	}

	@Override
	public void updateUserStudyStage(int user_id, int growthclass_id, int growthstage_id, int chapter_id) {
		((IGrowthStageDao) dao).updateUserStudyStage(user_id, growthclass_id, growthstage_id, chapter_id);
	}

	@Override
	public List<Map> listGrowthStageAndLebal(Integer growthclass_id) {
		List<Map> growthstageList = new ArrayList<Map>();
		List<GrowthStage> allGowthStageList = new ArrayList<>();
		if(null == growthclass_id){
			//查询全部阶段及所有标签
			allGowthStageList = findAll();
			
		}else{
			//查询指定阶段及所有标签
			allGowthStageList = findGrowthStageByGrowthClassId(growthclass_id);
		}
		if(0 != allGowthStageList.size()){
			for(GrowthStage growthStage:allGowthStageList){
				Map<String, Object> growthstageMap = new HashMap<String, Object>();
				if(null != growthStage){
					growthstageMap.put("growthStage", growthStage);
					growthstageMap.put("stageLabelList", stageLabelService.listStageLabelAndClassify(growthStage.getId()));
					growthstageMap.put("courseList", findCourseByGrowthStageId(growthStage.getId()));
				}
				growthstageList.add(growthstageMap);
			}
		}
		return growthstageList;
	}

	@Override
	public void delete(Integer id) {
		if(null != id){
			((IGrowthStageDao) dao).delete(id);
			
			List<StageLabel> stageLabelList = stageLabelService.findStageLabelByStageId(id);
			if(0 != stageLabelList.size()){
				for(StageLabel stageLabel:stageLabelList){
					//查找与阶段绑定的标签
					stageLabelService.deleteLableAndClassifyRelationByLabelId(stageLabel.getId());
					//查找与阶段绑定的课程
					((IGrowthStageDao) dao).deleteGrowthStageAndCourseRelationByStageId(stageLabel.getId());
				}
			}
			//删除与导航的绑定关系
			deleteGrowthClassAndStageRelationByClassId(id);
			//删除与阶段绑定的关系
			((IGrowthStageDao) dao).deleteStageAndLabelRelationByStageId(id);
		}
	}

	@Override
	public List<GrowthStage> findGrowthStageByGrowthClassId(int growthclass_id) {
		return ((IGrowthStageDao) dao).findGrowthStageByGrowthClassId(growthclass_id);
	}
	
	
}
