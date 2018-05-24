package org.ranji.lemon.volador.service.course.prototype;

import java.util.List;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.course.Note;
/**
 * 课程模块中INoteService接口
 * @author 范小亚
 *@version 1.0
 *@since JDK1.8
 *@date 2018/5/11
 */
public interface INoteService extends IGenericService<Note, Integer>{
	/**
	 * 保存笔记和用户的关系表
	 * @param noteId     笔记ID
	 * @param userId     用户ID
	 */
	public void saveNoteAndUserRelation(int noteId, int userId);
	
	/**
	 * 通过用户ID查询用户笔记
	 * @param userId        用户ID
	 * @return              用户笔记
	 */
    public List<Note> findNoteByUserId(int userId);
    
	/**
     * 通过笔记ID删除笔记用户关系表
     * @param noteId        笔记ID
     */
    public void deleteNoteAndUserRelationByNoteId(int noteId);

}
