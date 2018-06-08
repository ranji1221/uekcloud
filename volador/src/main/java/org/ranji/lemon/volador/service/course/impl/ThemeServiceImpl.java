package org.ranji.lemon.volador.service.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public Boolean deleteCourseAndThemeRelation(Integer themeId, Integer courseId) {
		Boolean bresult = true;
		if(null != themeId && null == courseId){
			//如果删除一个推荐主题，同时删除与主题相关的关系
			((IThemeDao) dao).delete(themeId);					
			((IThemeDao) dao).deleteCourseAndThemeAllRelationByThemdId(themeId);
			bresult = true;
		}else if(null != themeId && null != courseId){
			//删除指定主题及课程的关系
			((IThemeDao) dao).deleteCourseAndThemeRelation(themeId, courseId);
			bresult = true;
		}else{
			bresult = false;
		}
		return bresult;
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
	public Map<String, Object> findCourseByThemeId(Integer themeId) {
		Map<String, Object> themeAndcourseList = new HashMap<String, Object>();
		if(null == themeId){
			//查找全部主题及课程					
			List<Theme> themeList = ((IThemeDao) dao).findAll();
			for(Theme theme:themeList){
				themeAndcourseList.put("theme", theme);
				themeAndcourseList.put("courseList", ((IThemeDao) dao).findCourseByThemeId(theme.getId()));
			}
		}else{
			//查找指定主题的课程
			themeAndcourseList.put("theme", ((IThemeDao) dao).find(themeId));
			themeAndcourseList.put("courseList", ((IThemeDao) dao).findCourseByThemeId(themeId));
		}
		System.out.println(themeAndcourseList.toString());
		return themeAndcourseList;
	}

}
