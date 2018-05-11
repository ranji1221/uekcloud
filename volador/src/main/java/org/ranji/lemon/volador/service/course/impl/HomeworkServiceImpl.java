package org.ranji.lemon.volador.service.course.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Homework;
import org.ranji.lemon.volador.service.course.prototype.IHomeworkService;
import org.springframework.stereotype.Service;
/**
 * 学生作业Service实现类
 * @author 范小亚
 * @since JDK 1.8
 * @version 1.0
 * @date 2018/5/11
 */
@Service("VoladorCourseHomeworkServiceImpl")
public class HomeworkServiceImpl extends GenericServiceImpl<Homework, Integer> implements IHomeworkService{

}
