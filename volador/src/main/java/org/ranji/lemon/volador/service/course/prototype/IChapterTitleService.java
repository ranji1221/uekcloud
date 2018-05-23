package org.ranji.lemon.volador.service.course.prototype;

import java.util.List;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;

import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;

/**
 * @author sertion
 * @date 2018/5/22
 * @verison 1.0
 * @since JDK1.8
 */
public interface IChapterTitleService extends IGenericService<ChapterTitle, Integer>{
	
    /**
     * 根据章节标题id查找章节列表
     * @param chapter_title_id 课程Id
     * @return 章节对象集合
     */
    public List<Chapter> findChapterByChapterTitle(int chapter_title_id);

}
