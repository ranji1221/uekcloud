package org.ranji.lemon.volador.persist.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.persist.course.prototype.IClassifyDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorClassifyDaoImpl")	//-- 为解决其他项目中也有项目的类名，则利用@Autowired自动注入冲突的问题
public class ClassifyDaoImpl extends GenericDaoImpl<Classify, Integer> implements IClassifyDao {

	@Override
	public void saveClassifyAndCourseRelation(int course_id, int classify_id) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
        params.put("course_id",course_id);
        params.put("classify_id",classify_id);
        sqlSessionTemplate.insert(typeNameSpace+".saveClassifyAndCourseRelation",params);
		
	}

	@Override
	public void deleteClassifyAndCourseRelation(int course_id, int classify_id) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String, Object>();
        params.put("course_id",course_id);
        params.put("classify_id",classify_id);
        sqlSessionTemplate.delete(typeNameSpace+".deleteClassifyAndCourseRelation",params);
		
	}

	@Override
	public List<Course> findCoursebyClassify(int classify_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findCoursebyClassify", classify_id);
	}
}
