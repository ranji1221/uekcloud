package org.ranji.lemon.volador.persist.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Comment;
import org.ranji.lemon.volador.persist.course.prototype.IChapterDao;
import org.springframework.stereotype.Repository;

/**
 * @author sertion
 * @date 2018/5/9
 * @verison 1.0
 * @since JDK1.8
 */
@Repository("VoladorChapterDaoImpl")	//-- 为解决其他项目中也有项目的类名，则利用@Autowired自动注入冲突的问题
public class ChapterDaoImpl extends GenericDaoImpl<Chapter,Integer> implements IChapterDao{

	@Override
	public void saveChapterAndAccessoryRelation(int chapter_id, int accessory_id) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
        params.put("chapter_id",chapter_id);
        params.put("accessory_id",accessory_id);
        sqlSessionTemplate.insert(typeNameSpace+".saveChapterAndAccessoryRelation",params);
	}

	@Override
	public void deleteChapterAndAccessoryRelation(int chapter_id, int accessory_id) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
        params.put("chapter_id",chapter_id);
        params.put("accessory_id",accessory_id);
        sqlSessionTemplate.insert(typeNameSpace+".deleteChapterAndAccessoryRelation",params);
		
	}

	@Override
	public void saveChapterAndStudentNoteRelation(int chapter_id, int note_id) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
        params.put("chapter_id",chapter_id);
        params.put("note_id",note_id);
        sqlSessionTemplate.insert(typeNameSpace+".saveChapterAndStudentNoteRelation",params);
		
	}

	@Override
	public void deleteChapterAndStudentNoteRelation(int chapter_id, int note_id) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
        params.put("chapter_id",chapter_id);
        params.put("note_id",note_id);
        sqlSessionTemplate.insert(typeNameSpace+".deleteChapterAndStudentNoteRelation",params);
		
	}

	@Override
	public List<Comment> findCommentListByChapter(int chapter_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace+".findCommentListByChapter",chapter_id);
	}

	@Override
	public List<Integer> finCommentIdListByChapter(int chapter_id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
