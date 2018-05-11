package org.ranji.lemon.volador.persist.course.impl;

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

}
