package org.ranji.lemon.volador.service.course.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Theme;
import org.ranji.lemon.volador.persist.course.prototype.IThemeDao;
import org.ranji.lemon.volador.service.course.prototype.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VoladorThemeServiceImpl")
public class ThemeServiceImpl extends GenericServiceImpl<Theme, Integer> implements IThemeService{

	@Autowired
	private ClassifyServiceImpl classifyService;
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
	public List<Map> findCourseByThemeId(Integer themeId) {
		List<Map> themeAndcourseList = new ArrayList<Map>();
		if(null == themeId){
			//查找全部主题及课程					
			List<Theme> themeList = ((IThemeDao) dao).findAll();
			for(Theme theme:themeList){
				Map<String, Object> themeAndcourseMap = new HashMap<String, Object>();
				
				themeAndcourseMap.put("theme", theme);
				//获取分类下的所有课程
				List<Course> courseList = ((IThemeDao) dao).findCourseByThemeId(theme.getId());
				//获取首页显示课程信息
				List<Map> courseMap = getCourseAndClassify(courseList);
				themeAndcourseMap.put("courses", courseMap);
				themeAndcourseList.add(themeAndcourseMap);
			}
		}else{
			//查找指定主题的课程
			Map<String, Object> themeAndcourseMap = new HashMap<String, Object>();
			themeAndcourseMap.put("theme", ((IThemeDao) dao).find(themeId));
			//获取分类下的所有课程
			List<Course> courseList = ((IThemeDao) dao).findCourseByThemeId(themeId);
			//获取首页显示课程信息
			List<Map> courseMap = getCourseAndClassify(courseList);
			themeAndcourseMap.put("courses", courseMap);
			themeAndcourseList.add(themeAndcourseMap);
		}
		return themeAndcourseList;
	}
	
	// 获取课程及其对应分类
	public List<Map> getCourseAndClassify(List<Course> courseList) {
		List<Map> CourseAndClassifyList = new ArrayList<Map>();
		for (Course course : courseList) {
			HashMap<String, Object> courseMap = new HashMap<String, Object>();
			courseMap.put("courseId", course.getId());
			courseMap.put("courseName", course.getCourse_name());
			courseMap.put("course_price", course.getCourse_price());
			courseMap.put("student_count", course.getStudent_count());
			courseMap.put("image", course.getCourse_image_address());
			courseMap.put("classify", classifyService.findClassifyByCourseId(course.getId()).getClassify_name());
			System.out.println(courseMap.toString());
			System.out.println("-----------------");
			CourseAndClassifyList.add(courseMap);
		}

		return CourseAndClassifyList;
	}

}
