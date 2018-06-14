package org.ranji.lemon.volador.service.course.prototype;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;
import org.ranji.lemon.volador.model.course.Comment;
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
     * 存储课程与章节关系
     * @param course_id
     * @param chapter_id
     */
    public void saveCourseAndChapterRelation(int course_id,int chapter_id);

    /**
     * 删除课程与章节的对应关系
     * @param course_id
     * @param chapter_id
     */
    public void deleteCourseAndChapterRelation(int course_id,int chapter_id);

    /**
     * 根据课程ID查找Teacher
     * @param course_id 课程ID
     * @return 教师列表
     */
    public List<Teacher> findTeacherbyCourse(int course_id);
    
    /**
     * 根据课程ID查找Teacher
     * @param course_id 课程ID
     * @return 教师列表
     */
    public List<Chapter> findChapterbyCourse(int course_id);
    
    /**
     * 根据课程id查找章节标题列表
     * @param course_id 课程Id
     * @return 章节标题集合
     */
    public List<ChapterTitle> findChapterTitleByCourse(int course_id);
    
    /**
     * 根据课程id查找课程评论列表
     * @param course_id 课程Id
     * @return 评论集合
     */
    public List<Comment> findCommentListByCourse(int course_id);
    
    /**
     * 根据课程价格查询课程
     * @param price    课程价格
     * @return
     */
    public List<Course> findCourseByPrice(double price);
    
    /**
     * 关键字查询
     * @param keyword    课程价格
     * @return	List<Course>
     */
    public List<Course> keywordSreachCourse(String keyword);
    
    /**
     * 查询正在学习人数
     * @param courseId    课程Id
     * @return	int
     */
    public int findStudyingStudent(int courseId);
    
    /**
     * 查询正在课程总时长
     * @param courseId    课程Id
     * @return	int
     */
    public int findCourseTotalTime(int courseId);
    
}
