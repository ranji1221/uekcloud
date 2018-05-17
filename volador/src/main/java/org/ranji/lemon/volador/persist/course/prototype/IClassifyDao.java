package org.ranji.lemon.volador.persist.course.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Course;

public interface IClassifyDao extends IGenericDao<Classify, Integer> {
	
    /**
     * 储存课程分类-课程的对应
     * @param classify_id 课程分类ID
     * @param course_id 课程ID
     */
    public void saveClassifyAndCourseRelation(int classify_id,int course_id);

    /**
     *删除课程-课程的对应
     * @param classify_id 课程分类Id
     * @param course_id 课程ID
     */
    public void deleteClassifyAndCourseRelation(int classify_id,int course_id);
    
    /**
     *根据课程分类ID查找课程
     * @param classify_id 课程分类Id
     * @return Course列表
     */
    public List<Course> findCoursebyClassify(int classify_id);
	

}
