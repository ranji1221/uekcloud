package org.ranji.lemon.volador.persist.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Theme;
import org.ranji.lemon.volador.persist.course.prototype.IThemeDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorThemeDaoImpl")
public class ThemeDaoImpl extends GenericDaoImpl<Theme, Integer> implements IThemeDao{

	@Override
	public void saveCourseAndThemeRelation(int themeId, int courseId) {
		Map<String,Object> params=new HashMap<String,Object>();
        params.put("theme_id",themeId);
        params.put("course_id",courseId);
        sqlSessionTemplate.insert(typeNameSpace+".saveCourseAndThemeRelation",params);		
	}

	@Override
	public void deleteCourseAndThemeRelation(int themeId, int courseId) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("theme_id",themeId);
        params.put("course_id",courseId);
        sqlSessionTemplate.delete(typeNameSpace+".deleteCourseAndThemeRelation",params);
	}

	@Override
	public void deleteCourseAndThemeAllRelationByThemdId(int themeId) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("theme_id",themeId);
        sqlSessionTemplate.delete(typeNameSpace+".deleteCourseAndThemeAllRelationByThemdId",params);
	}

	@Override
	public void deleteCourseAndThemeAllRelationByCourseId(int courseId) {
		Map<String,Object> params=new HashMap<String, Object>();
        params.put("course_id",courseId);
        sqlSessionTemplate.delete(typeNameSpace+".deleteCourseAndThemeAllRelationByCourseId",params);
	}

	@Override
	public List<Course> findCourseAndThemeRelationByCourseId(int themeId) {
		return sqlSessionTemplate.selectList(typeNameSpace + ".findCourseAndThemeRelationByCourseId", themeId);
	}

}
