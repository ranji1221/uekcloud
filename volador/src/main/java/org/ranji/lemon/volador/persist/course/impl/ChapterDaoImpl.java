package org.ranji.lemon.volador.persist.course.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Chapter;
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

}
