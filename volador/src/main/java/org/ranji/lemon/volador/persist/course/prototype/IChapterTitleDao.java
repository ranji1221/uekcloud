package org.ranji.lemon.volador.persist.course.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.ChapterTitle;

/**
 * @author sertion
 * @date 2018/5/22
 * @since JDK1.8
 * @version 1.0
 */
public interface IChapterTitleDao extends IGenericDao<ChapterTitle,Integer>{

    
    /**
     * 根据章节标题id查找章节列表
     * @param chapter_title_id 课程Id
     * @return 章节对象集合
     */
    public List<Chapter> findChapterByChapterTitle(int chapter_title_id);
}
