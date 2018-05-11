package org.ranji.lemon.volador.persist.course.prototype;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Teacher;

import java.util.List;

/**
 * @author sertion
 * @date 2018/5/9
 * @since JDK1.8
 * @version 1.0
 */
public interface ICourseDao extends IGenericDao<Course,Integer>{
    /**
     * 储存课程-老师的对应
     * @param course_id 课程ID
     * @param teacher_id 教师ID
     */
    public void saveCourseAndTeacherRelation(int course_id,int teacher_id);

    /**
     *删除课程-老师的对应
     * @param course_id 课程Id
     * @param teacher_id 教师
     */
    public void deleteCourseAndTeacherRelation(int course_id,int teacher_id);
    /**
     * 储存课程-章节的对应
     * @param course_id 课程ID
     * @param chapter_id 章节ID
     */
    public void saveCourseAndChapterRelation(int course_id,int chapter_id);

    /**
     *删除课程-章节的对应
     * @param course_id 课程Id
     * @param chapter_id 章节
     */
    public void deleteCourseAndChapterRelation(int course_id,int chapter_id);

    /**
     *根据课程id查找关联教师
     * @param course_id 课程
     * @return 教师集合
     */
    public List<Teacher> findTeacherbyCourse(int course_id);

    /**
     * 根据课程id查找关联章节
     * @param course_id 课程Id
     * @return 章节集合
     */
    public List<Chapter> findChapterbyCourse(int course_id);
}
