package org.ranji.lemon.volador.service.course.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;
import org.ranji.lemon.volador.persist.course.prototype.IChapterTitleDao;
import org.ranji.lemon.volador.service.course.prototype.IChapterTitleService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sertion
 * @date 2018/5/22
 * @verison 1.0
 * @since JDK1.8
 */

@Service("VoladorChapterTitleServiceImpl")  //-- 为解决其他项目中也有项目的类名，则利用@Autowired自动注入冲突的问题
public class ChapterTitleService extends GenericServiceImpl<ChapterTitle, Integer> implements IChapterTitleService{

    @Autowired
    private IChapterTitleService chapterTitleService;

	@Override
	public List<Chapter> findChapterByChapterTitle(int chapter_title_id) {
		// TODO Auto-generated method stub
		return ((IChapterTitleDao) dao).findChapterByChapterTitle(chapter_title_id);
	}
}
