package org.ranji.lemon.volador.persist.course.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.persist.course.prototype.ITeacherDao;
import org.springframework.stereotype.Repository;

/**
 * @author sertion
 * @date 2018/5/10
 * @verison 1.0
 * @since JDK1.8
 */
@Repository("VoladorTeacherDaoImpl")
public class TeacherDaoImpl extends GenericDaoImpl<Teacher,Integer> implements ITeacherDao {

}
