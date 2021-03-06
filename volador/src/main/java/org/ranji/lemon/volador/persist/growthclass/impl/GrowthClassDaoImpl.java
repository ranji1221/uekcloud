package org.ranji.lemon.volador.persist.growthclass.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.growthclass.GrowthClass;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
import org.ranji.lemon.volador.persist.growthclass.prototype.IGrowthClassDao;
import org.springframework.stereotype.Repository;
/**
 * 成长体系Dao实现类
 * @author 范小亚
 * @date 2018/6/1
 * @since JDK1.8
 * @version 1.0
 */
@Repository("VoladorGrowthClassDaoImpl")
public class GrowthClassDaoImpl extends GenericDaoImpl<GrowthClass, Integer> implements IGrowthClassDao{

	@Override
	public void saveGrowthClassAndStageRelation(int class_id, int stage_id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("class_id", class_id);
		param.put("stage_id", stage_id);
		sqlSessionTemplate.insert(typeNameSpace+".saveGrowthClassAndStageRelation", param);		
	}

	@Override
	public void deleteGrowthClassAndStageRelation(int class_id, int stage_id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("class_id", class_id);
		param.put("stage_id", stage_id);
		sqlSessionTemplate.delete(typeNameSpace+".deleteGrowthClassAndStageRelation", param);
	}

	@Override
	public void deleteGrowthClassAndStageRelationByClassId(int class_id) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteGrowthClassAndStageRelationByClassId", class_id);
	}

	@Override
	public void updateGrowthClassAndStageRelation(int class_id, int stage_id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("class_id", class_id);
		param.put("stage_id", stage_id);
		sqlSessionTemplate.update(typeNameSpace+".updateGrowthClassAndStageRelation", param);
		
	}

	@Override
	public List<GrowthStage> findGrowthStageByGrowthClassId(int class_id) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findGrowthStageByGrowthClassId", class_id);
	}

	@Override
	public List<GrowthClass> findGrowthClassByGrowthStageId(int stage_id) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findGrowthClassByGrowthStageId", stage_id);
	}

	@Override
	public void saveUserAndGrowthClassRelation(int user_id, int growthclass_id, int chapter_id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_id", user_id);
		param.put("growthclass_id", growthclass_id);
		param.put("chapter_id", chapter_id);
		sqlSessionTemplate.insert(typeNameSpace+".saveUserAndGrowthClassRelation", param);
	}

	@Override
	public void updateUserAndGrowthClassRelation(int user_id, int growthclass_id, int chapter_id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_id", user_id);
		param.put("growthclass_id", growthclass_id);
		param.put("chapter_id", chapter_id);
		sqlSessionTemplate.insert(typeNameSpace+".updateUserAndGrowthClassRelation", param);
	}

	@Override
	public List<GrowthClass> findGrowthClassByUserId(int user_id) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findGrowthClassByUserId", user_id);
	}

	@Override
	public List<Chapter> findChapterByUserIdAndGrowthClassId(int user_id, int growthclass_id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("user_id", user_id);
		param.put("growthclass_id", growthclass_id);
		return sqlSessionTemplate.selectList(typeNameSpace+".findChapterByUserIdAndGrowthClassId", param);
	}

	@Override
	public List<GrowthClass> findGrowthClassByCourseId(int course_id) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findGrowthClassByCourseId", course_id);
	}

}
