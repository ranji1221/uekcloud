package org.ranji.lemon.volador.persist.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Direction;
import org.ranji.lemon.volador.persist.course.prototype.IDirectionDao;
import org.springframework.stereotype.Repository;

/**
 * 课程方向
 * @author 范小亚
 * @date 2018/5/28
 * @since JDK1.8
 * @version 1.0
 */
@Repository("VoladorCourseDirectionDaoImpl")
public class DirectionDaoImpl extends GenericDaoImpl<Direction, Integer> implements IDirectionDao{

	@Override
	public List<Integer> findDirectionIdByClassiyId(int classifyId) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findDirectionIdByClassiyId", classifyId);
	}

	@Override
	public void saveDirectionAndClassifyRelation(int directionId, int classifyId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("directionId", directionId);
		map.put("classifyId", classifyId);
		sqlSessionTemplate.insert(typeNameSpace+ ".saveDirectionAndClassifyRelation", map);		
	}

	@Override
	public void deleteDirectionAndClassifyRelation(int directionId, int classifyId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("directionId", directionId);
		map.put("classifyId", classifyId);
		sqlSessionTemplate.delete(typeNameSpace+".deleteDirectionAndClassifyRelation", map);
	}

	@Override
	public void deleteDirectionAndClassifyRelationByDirectionId(int directionId) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteDirectionAndClassifyRelation", directionId);
	}

	@Override
	public List<Classify> findClassifyByDirectionId(int directionId) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findClassifyByDirectionId", directionId);
	}

}
