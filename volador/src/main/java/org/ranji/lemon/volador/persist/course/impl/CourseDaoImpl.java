package org.ranji.lemon.volador.persist.course.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;
import org.ranji.lemon.volador.model.course.Comment;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.persist.course.prototype.ICourseDao;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sertion
 * @date 2018/5/9
 * @verison 1.0
 * @since JDK1.8
 */
@Repository("VoladorCourseDaoImpl")	//-- 为解决其他项目中也有项目的类名，则利用@Autowired自动注入冲突的问题
public class CourseDaoImpl extends GenericDaoImpl<Course,Integer> implements ICourseDao{

    @Override
    public void saveCourseAndTeacherRelation(int course_id, int teacher_id) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("course_id",course_id);
        params.put("teacher_id",teacher_id);
        sqlSessionTemplate.insert(typeNameSpace+".saveCourseAndTeacherRelation",params);
    }

    @Override
    public void deleteCourseAndTeacherRelation(int course_id, int teacher_id) {

        Map<String,Object> params=new HashMap<String, Object>();
        params.put("course_id",course_id);
        params.put("teacher_id",teacher_id);
        sqlSessionTemplate.delete(typeNameSpace+".deleteCourseAndTeacherRelation",params);
    }

    @Override
    public List<Teacher> findTeacherbyCourse(int course_id) {
    	return sqlSessionTemplate.selectList(typeNameSpace + ".findTeacherbyCourse", course_id);
    }

    @Override
    public List<Chapter> findChapterbyCourse(int course_id) {
    	return sqlSessionTemplate.selectList(typeNameSpace + ".findChapterbyCourse", course_id);
    }

	@Override
	public void saveCourseAndChapterRelation(int course_id, int chapter_id) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
        params.put("course_id",course_id);
        params.put("chapter_id",chapter_id);
        sqlSessionTemplate.insert(typeNameSpace+".saveCourseAndChapterRelation",params);
		
	}

	@Override
	public void deleteCourseAndChapterRelation(int course_id, int chapter_id) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
        params.put("course_id",course_id);
        params.put("chapter_id",chapter_id);
        sqlSessionTemplate.delete(typeNameSpace+".deleteCourseAndChapterRelation",params);
		
	}

	@Override
	public List<ChapterTitle> findChapterTitleByCourse(int course_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace + ".findChapterTitleByCourse", course_id);
	}

	@Override
	public List<Comment> findCommentListByCourse(int course_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace+".findCommentListByCourse", course_id);
	}
	
	
}
