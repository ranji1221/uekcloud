package org.ranji.lemon.volador.persist.course.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Note;
/**
 * 笔记Dao接口
 * @author 范小亚
 * @date 2018/5/11
 * @since JDK 1.8
 * @version 1.0
 *
 */
public interface INoteDao extends IGenericDao<Note, Integer>{
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
