package org.ranji.lemon.volador.service.course.prototype;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Teacher;

import java.util.List;

/**
 * @author sertion
 * @date 2018/5/9
 * @verison 1.0
 * @since JDK1.8
 */
public interface ICourseService extends IGenericService<Course,Integer> {

    /**
     * 存储课程与教师关系
     * @param course_id
     * @param teacher_id
     */
    public void saveCourseAndTeacherRelation(int course_id,int teacher_id);

    /**
     * 删除课程与教师的对应关系
     * @param course_id
     * @param teacher_id
     */
    public void deleteCourseAndTeacherRelation(int course_id,int teacher_id);

    /**
     * 根据课程ID查找Teacher
     * @param course_id 课程ID
     * @return 教师列表
     */
    public List<Teacher> findTeacherbyCourse(int course_id);
}
