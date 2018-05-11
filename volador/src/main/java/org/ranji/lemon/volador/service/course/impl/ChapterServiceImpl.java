package org.ranji.lemon.volador.service.course.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Chapter;
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
}
