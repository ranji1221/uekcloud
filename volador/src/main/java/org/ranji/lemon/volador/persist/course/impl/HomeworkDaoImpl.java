package org.ranji.lemon.volador.persist.course.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Homework;
import org.ranji.lemon.volador.persist.course.prototype.IHomeworkDao;
import org.springframework.stereotype.Repository;
/**
 * 学生作业接口实现
 * @author 范小亚
 * @since JDK 1.8
 * @version 1.0
 * @date 2018/5/11
 */
@Repository("VoladorCourseHomeworkImpl")
public class HomeworkDaoImpl extends GenericDaoImpl<Homework, Integer> implements IHomeworkDao{

}
