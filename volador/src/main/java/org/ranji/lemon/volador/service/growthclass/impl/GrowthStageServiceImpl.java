package org.ranji.lemon.volador.service.growthclass.impl;


import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
import org.ranji.lemon.volador.persist.growthclass.prototype.IGrowthStageDao;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthStageService;
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

	@Override
	public void saveGrowthStageAndCourseRelation(int stage_id, int course_id) {
		((IGrowthStageDao) dao).saveGrowthStageAndCourseRelation(stage_id, course_id);		
	}

	@Override
	public void deleteGrowthStageAndCourseRelation(int stage_id, int course_id) {
		((IGrowthStageDao) dao).deleteGrowthStageAndCourseRelation(stage_id, course_id);
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
	public void saveUserStudyStage(int user_id, int growthclass_id, int growthstage_id, int chapter_id) {
		((IGrowthStageDao) dao).saveUserStudyStage(user_id, growthstage_id, chapter_id, chapter_id);
	}

	@Override
	public Chapter findChapterByUserIdAndClassIdAndStageId(int user_id, int growthclass_id, int growthstage_id) {	
		try{
			return ((IGrowthStageDao) dao).findChapterByUserIdAndClassIdAndStageId(user_id, growthclass_id, growthstage_id).get(0);
		}catch(Exception e){
			return null;
		}
	}
}
