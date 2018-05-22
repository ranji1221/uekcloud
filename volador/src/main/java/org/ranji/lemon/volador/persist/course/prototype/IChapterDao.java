package org.ranji.lemon.volador.persist.course.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Comment;

/**
 * @author sertion
 * @date 2018/5/9
 * @since JDK1.8
 * @version 1.0
 */
public interface IChapterDao extends IGenericDao<Chapter,Integer> {
	
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
