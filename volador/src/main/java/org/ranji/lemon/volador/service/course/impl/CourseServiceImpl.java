package org.ranji.lemon.volador.service.course.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.persist.course.prototype.ICourseDao;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author sertion
 * @date 2018/5/9
 * @verison 1.0
 * @since JDK1.8
 */

@Service("VoladorCourseServiceImpl")  //-- 为解决其他项目中也有项目的类名，则利用@Autowired自动注入冲突的问题
public class CourseServiceImpl extends GenericServiceImpl<Course,Integer> implements ICourseService {

    @Autowired
    ICourseService courseService;

    @Override
    public void saveCourseAndTeacherRelation(int course_id, int teacher_id) {
        ((ICourseDao) dao).saveCourseAndTeacherRelation(course_id,teacher_id);
        }

	@Override
	public void deleteCourseAndTeacherRelation(int course_id, int teacher_id) {
		// TODO Auto-generated method stub
		((ICourseDao) dao).deleteCourseAndTeacherRelation(course_id,teacher_id);
	}

	@Override
	public List<Teacher> findTeacherbyCourse(int course_id) {
		// TODO Auto-generated method stub
		return ((ICourseDao) dao).findTeacherbyCourse(course_id);
	}

	@Override
	public List<Chapter> findChapterbyCourse(int course_id) {
		// TODO Auto-generated method stub
		return ((ICourseDao) dao).findChapterbyCourse(course_id);
	}

	@Override
	public void saveCourseAndChapterRelation(int course_id, int chapter_id) {
		// TODO Auto-generated method stub
		((ICourseDao) dao).saveCourseAndChapterRelation(course_id, chapter_id);
		
	}

	@Override
	public void deleteCourseAndChapterRelation(int course_id, int chapter_id) {
		// TODO Auto-generated method stub
		((ICourseDao) dao).deleteCourseAndChapterRelation(course_id, chapter_id);
	}

	@Override
	public List<ChapterTitle> findChapterTitleByCourse(int course_id) {
		// TODO Auto-generated method stub
		return ((ICourseDao) dao).findChapterTitleByCourse(course_id);
	}


	

	

	


}
