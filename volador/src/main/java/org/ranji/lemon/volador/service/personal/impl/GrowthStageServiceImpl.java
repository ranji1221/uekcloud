package org.ranji.lemon.volador.service.personal.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.personal.GrowthStage;
import org.ranji.lemon.volador.persist.personal.prototype.IGrowthStageDao;
import org.ranji.lemon.volador.service.personal.prototype.IGrowthStageService;
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

}
