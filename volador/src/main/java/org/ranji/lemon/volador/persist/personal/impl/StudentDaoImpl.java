package org.ranji.lemon.volador.persist.personal.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.personal.Student;
import org.ranji.lemon.volador.persist.personal.prototype.IStudentDao;
import org.springframework.stereotype.Repository;
/**
 * 学员Dao层实现
 * @author 范小亚
 * @date 2018/6/4
 * @since JDK1.8
 * @version 1.0
 */
@Repository("VoladorStudentDaoImpl")
public class StudentDaoImpl extends GenericDaoImpl<Student, Integer> implements IStudentDao{

}
