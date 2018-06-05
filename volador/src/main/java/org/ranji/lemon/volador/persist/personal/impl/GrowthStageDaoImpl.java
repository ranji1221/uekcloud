package org.ranji.lemon.volador.persist.personal.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.personal.GrowthStage;
import org.ranji.lemon.volador.persist.personal.prototype.IGrowthStageDao;
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

}
