package org.ranji.lemon.volador.persist.course.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Theme;

public interface IThemeDao extends IGenericDao<Theme, Integer>{
	
	/**
	 * 保存首页课程分类主题及对应课程的关系表
	 * @param themeId  课程分类主题ID
	 * @param courseId 课程ID
	 */
	public void saveCourseAndThemeRelation(int themeId, int courseId);
	
	/**
	 * 删除指定首页课程分类与课程的关系
	 * @param themeId   课程分类主题ID
	 * @param courseId  课程ID
	 */
	public void deleteCourseAndThemeRelation(int themeId, int courseId);
	
	/**
	 * 根据首页课程分类删除该分类的所与关系表
	 * @param themeId  课程分类主题ID
	 */
	public void deleteCourseAndThemeAllRelationByThemdId(int themeId);
	
	/**
	 * 根据课程ID删除该分类的所与关系表
	 * @param courseId 课程ID
	 */
	public void deleteCourseAndThemeAllRelationByCourseId(int courseId);
	
	/**
	 * 根据首页课程分类ID查找该分类下所有的课程
	 * @param themeId  课程分类主题ID
	 * @return  课程列表
	 */
	public List<Course> findCourseAndThemeRelationByCourseId(int themeId);

}
