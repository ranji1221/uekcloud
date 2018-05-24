package org.ranji.lemon.volador.service.course.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Note;
import org.ranji.lemon.volador.persist.course.prototype.INoteDao;
import org.ranji.lemon.volador.service.course.prototype.INoteService;
import org.springframework.stereotype.Service;
/**
 * NoteService实现类
 * @author 范小亚
 * @since JDK1.8
 * @date 2018/5/11
 * @version 1.0
 *
 */
@Service("VoladorCourseNoteServiceImpl")
public class NoteServiceImpl extends GenericServiceImpl<Note, Integer> implements INoteService{

	@Override
	public void saveNoteAndUserRelation(int noteId, int userId) {
		((INoteDao) dao).saveNoteAndUserRelation(noteId,userId);
		
	}

	@Override
	public List<Note> findNoteByUserId(int userId) {
		return ((INoteDao) dao).findNoteByUserId(userId);
	}

	@Override
	public void deleteNoteAndUserRelationByNoteId(int noteId) {
		((INoteDao) dao).deleteNoteAndUserRelationByNoteId(noteId);
		
	}

}
