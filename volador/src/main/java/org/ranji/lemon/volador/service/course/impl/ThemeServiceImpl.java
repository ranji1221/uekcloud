package org.ranji.lemon.volador.service.course.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Theme;
import org.ranji.lemon.volador.persist.course.prototype.IThemeDao;
import org.ranji.lemon.volador.service.course.prototype.IThemeService;
import org.springframework.stereotype.Service;

@Service("VoladorThemeServiceImpl")
public class ThemeServiceImpl extends GenericServiceImpl<Theme, Integer> implements IThemeService{

	@Override
	public void saveCourseAndThemeRelation(int themeId, int courseId) {
		((IThemeDao) dao).saveCourseAndThemeRelation(themeId,courseId);
	}

	@Override
	public void deleteCourseAndThemeRelation(int themeId, int courseId) {
		((IThemeDao) dao).deleteCourseAndThemeRelation(themeId,courseId);
	}

	@Override
	public void deleteCourseAndThemeAllRelationByThemdId(int themeId) {
		((IThemeDao) dao).deleteCourseAndThemeAllRelationByThemdId(themeId);
	}

	@Override
	public void deleteCourseAndThemeAllRelationByCourseId(int courseId) {
		((IThemeDao) dao).deleteCourseAndThemeAllRelationByCourseId(courseId);
	}

	@Override
	public List<Course> findCourseAndThemeRelationByCourseId(int themeId) {
		return ((IThemeDao) dao).findCourseAndThemeRelationByCourseId(themeId);
	}

}
