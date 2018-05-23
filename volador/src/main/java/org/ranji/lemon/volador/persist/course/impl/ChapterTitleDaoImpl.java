package org.ranji.lemon.volador.persist.course.impl;

import java.util.List;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;
import org.ranji.lemon.volador.persist.course.prototype.IChapterTitleDao;
import org.springframework.stereotype.Repository;

/**
 * @author sertion
 * @date 2018/5/22
 * @verison 1.0
 * @since JDK1.8
 */

@Repository("VoladorChapterTitleDaoImpl")	//-- 为解决其他项目中也有项目的类名，则利用@Autowired自动注入冲突的问题
public class ChapterTitleDaoImpl extends GenericDaoImpl<ChapterTitle, Integer> implements IChapterTitleDao{

	@Override
	public List<Chapter> findChapterByChapterTitle(int chapter_title_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace +".findChapterByChapterTitle", chapter_title_id);
	}

}
