package org.ranji.lemon.volador.service.course.prototype;

import java.util.List;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Comment;
import org.ranji.lemon.volador.model.course.Teacher;

/**
 * @author sertion
 * @date 2018/5/10
 * @verison 1.0
 * @since JDK1.8
 */
public interface IChapterService extends IGenericService<Chapter,Integer> {
	
    /**
     * 储存章节-附件的对应
     * @param chapter_id 课程ID
     * @param accessory_id 教师ID
     */
    public void saveChapterAndAccessoryRelation(int chapter_id,int accessory_id);

    /**
     *删除章节-附件的对应
     * @param chapter_id 课程Id
     * @param accessory_id 教师
     */
    public void deleteChapterAndAccessoryRelation(int chapter_id,int accessory_id);
    
    /**
     * 储存章节-学生笔记的对应
     * @param chapter_id 课程ID
     * @param note_id 教师ID
     */
    public void saveChapterAndStudentNoteRelation(int chapter_id,int note_id);

    /**
     *删除章节-学生笔记的对应
     * @param chapter_id 课程Id
     * @param note_id 教师
     */
    public void deleteChapterAndStudentNoteRelation(int chapter_id,int note_id);
    
    /**
     * 根据章节id查找评论列表
     * @param chapter_id 章节ID
     * @return 评论列表
     */
    public List<Comment> findCommentListByChapter(int chapter_id);
    
    /**
     * 根据章节id查找评论id列表
     * @param chapter_id 章节ID
     * @return 评论id列表
     */
    public List<Integer> finCommentIdListByChapter(int chapter_id);
    
    
    
	
}
