package org.ranji.lemon.volador.service.course.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.persist.course.prototype.IChapterDao;
import org.ranji.lemon.volador.persist.course.prototype.ICourseDao;
import org.ranji.lemon.volador.service.course.prototype.IChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sertion
 * @date 2018/5/10
 * @verison 1.0
 * @since JDK1.8
 */
@Service("VoladorChapterServiceImpl")
public class ChapterServiceImpl extends GenericServiceImpl<Chapter,Integer> implements IChapterService{
    @Autowired
    IChapterService chapterService;

	@Override
	public void saveChapterAndAccessoryRelation(int chapter_id, int accessory_id) {
		// TODO Auto-generated method stub
		((IChapterDao) dao).saveChapterAndAccessoryRelation(chapter_id, accessory_id);
	}

	@Override
	public void deleteChapterAndAccessoryRelation(int chapter_id, int accessory_id) {
		// TODO Auto-generated method stub
		((IChapterDao) dao).deleteChapterAndAccessoryRelation(chapter_id, accessory_id);
	}

	@Override
	public void saveChapterAndStudentNoteRelation(int chapter_id, int note_id) {
		// TODO Auto-generated method stub
		((IChapterDao) dao).saveChapterAndStudentNoteRelation(chapter_id, note_id);
	}

	@Override
	public void deleteChapterAndStudentNoteRelation(int chapter_id, int note_id) {
		// TODO Auto-generated method stub
		((IChapterDao) dao).deleteChapterAndStudentNoteRelation(chapter_id, note_id);
		
	}
}
