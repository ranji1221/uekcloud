package org.ranji.lemon.volador.service.course.prototype;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.course.Chapter;

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
	
}
