package org.ranji.lemon.volador.persist.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.persist.course.prototype.ITeacherDao;
import org.springframework.stereotype.Repository;

/**
 * @author sertion
 * @date 2018/5/10
 * @verison 1.0
 * @since JDK1.8
 */
@Repository("VoladorTeacherDaoImpl")
public class TeacherDaoImpl extends GenericDaoImpl<Teacher,Integer> implements ITeacherDao {

	@Override
	public void saveHeaderTeacher(int teacher_id) {
		sqlSessionTemplate.insert(typeNameSpace+".saveHeaderTeacher", teacher_id);
		
	}

	@Override
	public List<Teacher> findHeaderTeacher() {
		return sqlSessionTemplate.selectList(typeNameSpace+".findHeaderTeacher", null);
	}
	
	@Override
	public List<Teacher> findTeacherInfoByPage(int page, int limit) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("offset", (page-1)*limit);
		params.put("limit", limit);
		return sqlSessionTemplate.selectList(typeNameSpace+".findTeacherInfoByPage", params);
	}

	@Override
	public void deleteHeaderTeacher(Integer teacher_id) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteHeaderTeacher", teacher_id);
	}

	@Override
	public int findTeacherCount() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace+".findTeacherCount");
	}

	@Override
	public void saveGrowthClassTeacher(int growthclass_id, int teacher_id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("growthclass_id", growthclass_id);
		params.put("teacher_id", teacher_id);
		
		sqlSessionTemplate.insert(typeNameSpace+".saveGrowthClassTeacher", params);
	}

	@Override
	public void deleteGrowthClassTeacher(int growthclass_id, int teacher_id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("growthclass_id", growthclass_id);
		params.put("teacher_id", teacher_id);
		
		sqlSessionTemplate.delete(typeNameSpace+".deleteGrowthClassTeacher", params);
	}

	@Override
	public List<Teacher> findTeacherByGrowthClassId(int growthclass_id) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findTeacherByGrowthClassId", growthclass_id);
	}

}
