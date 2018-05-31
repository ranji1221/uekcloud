package org.ranji.lemon.volador.service.course.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Course;

public interface IClassifyService extends IGenericDao<Classify, Integer> {
	
    /**
     * 存储课程分类与课程关系
     * @param course_id
     * @param classify_id
     */
    public void saveClassifyAndCourseRelation(int course_id,int classify_id);

    /**
     * 删除课程分类与课程的对应关系
     * @param course_id
     * @param classify_id
     */
    public void deleteClassifyAndCourseRelation(int course_id,int classify_id);
    
    /**
     * 根据课程分类ID查找Course
     * @param classify_id 课程分类ID
     * @return 课程列表
     */
    public List<Course> findCourseByClassify(int classify_id);
    
    /**
     * 根据课程ID查找分类
     * @param course_id   课程ID
     * @return
     */
    public Classify findClassifyByCourseId(int course_id);

}
