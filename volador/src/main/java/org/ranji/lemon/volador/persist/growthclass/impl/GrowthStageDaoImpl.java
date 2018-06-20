package org.ranji.lemon.volador.persist.growthclass.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
import org.ranji.lemon.volador.persist.growthclass.prototype.IGrowthStageDao;
import org.springframework.stereotype.Repository;
/**
 * 成长阶段Dao实现
 * @author 范小亚
 * @date 2018/6/1
 * @since JDK1.8
 * @version 1.0
 */
@Repository("VoladorGrowthStageDaoImpl")
public class GrowthStageDaoImpl extends GenericDaoImpl<GrowthStage, Integer> implements IGrowthStageDao{

	@Override
	public void saveGrowthStageAndCourseRelation(int stage_id, int course_id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("stage_id", stage_id);
		parameter.put("course_id", course_id);
		sqlSessionTemplate.insert(typeNameSpace+".saveGrowthStageAndCourseRelation", parameter);
		
	}

	@Override
	public void deleteGrowthStageAndCourseRelation(int stage_id, int course_id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("stage_id", stage_id);
		parameter.put("course_id", course_id);
		sqlSessionTemplate.delete(typeNameSpace+".deleteGrowthStageAndCourseRelation", parameter);
	}

	@Override
	public void deleteGrowthClassAndStageRelationByStageId(int stage_id) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteGrowthClassAndStageRelationByStageId", stage_id);
	}

	@Override
	public void deleteGrowthStageAndCourseRelationByStageId(int stage_id) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteGrowthStageAndCourseRelationByStageId", stage_id);		
	}

	@Override
	public void updateGrowthStageAndCourseRelation(int stage_id, int course_id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("stage_id", stage_id);
		parameter.put("course_id", course_id);
		sqlSessionTemplate.update(typeNameSpace+".updateGrowthStageAndCourseRelation", parameter);
	}

	@Override
	public List<Course> findCourseByGrowthStageId(int stage_id) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findCourseByGrowthStageId", stage_id);
	}

	@Override
	public void saveUserStudyStage(Integer user_id, Integer growthclass_id, Integer growthstage_id, Integer chapter_id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("growthstage_id", growthstage_id);
		parameter.put("user_id", user_id);
		parameter.put("chapter_id", chapter_id);
		parameter.put("growthclass_id", growthclass_id);
		sqlSessionTemplate.insert(typeNameSpace+".saveUserStudyStage", parameter);
	}

	@Override
	public List<GrowthStage> findStudyStageByUserIdAndChapterId(int user_id, int growthclass_id, int course_id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("user_id", user_id);
		parameter.put("chapter_id", course_id);
		parameter.put("growthclass_id", growthclass_id);
		return sqlSessionTemplate.selectList(typeNameSpace+".findStudyStageByUserIdAndChapterId", parameter);
	}

	@Override
	public void updateUserStudyStage(int user_id, int growthclass_id, int growthstage_id, int chapter_id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("growthstage_id", growthstage_id);
		parameter.put("user_id", user_id);
		parameter.put("chapter_id", chapter_id);
		parameter.put("growthclass_id", growthclass_id);
		sqlSessionTemplate.update(typeNameSpace+".updateUserStudyStage", parameter);
	}

	@Override
	public List<Chapter> findChapterByUserIdAndClassIdAndStageId(int user_id, int growthclass_id, int growthstage_id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("growthstage_id", growthstage_id);
		parameter.put("user_id", user_id);
		parameter.put("growthclass_id", growthclass_id);
		return sqlSessionTemplate.selectList(typeNameSpace+".findChapterByUserIdAndClassIdAndStageId", parameter);
	}

	@Override
	public List<GrowthStage> findGrowthStageByCourseId(int course_id) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findGrowthStageByCourseId", course_id);
	}

	@Override
	public void deleteStageAndLabelRelationByStageId(int growthstage_id) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteStageAndLabelRelationByStageId", growthstage_id);
	}

	@Override
	public List<GrowthStage> findGrowthStageByGrowthClassId(int growthclass_id) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findGrowthStageByGrowthClassId", growthclass_id);
	}
}
