package org.ranji.lemon.volador.persist.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Note;
import org.ranji.lemon.volador.persist.course.prototype.INoteDao;
import org.springframework.stereotype.Repository;
/**
 * 笔记Dao接口实现类
 * @author 范小亚
 * @date 2018/5/11
 * @since JDK 1.8
 * @version 1.0
 *
 */
@Repository("VoladorCourseNote")
public class NoteDaoImpl extends GenericDaoImpl<Note, Integer> implements INoteDao{

	@Override
	public void saveNoteAndUserRelation(int noteId, int userId) {
		Map<String, Object> map = new HashMap();
		map.put("noteId", noteId);
		map.put("userId", userId);
		sqlSessionTemplate.insert(typeNameSpace+".saveNoteAndUserRelation", map);
		
	}

	@Override
	public List<Note> findNoteByUserId(int userId,int chapterId) {
		Map<String,Object> map=new HashMap();
		map.put("id", userId);
		map.put("chapterId", chapterId);
		return sqlSessionTemplate.selectList(typeNameSpace+".findNoteByUserId", map);
	}

	@Override
	public void deleteNoteAndUserRelationByNoteId(int NoteId) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteNoteAndUserRelationByNoteId", NoteId);
	}

}
