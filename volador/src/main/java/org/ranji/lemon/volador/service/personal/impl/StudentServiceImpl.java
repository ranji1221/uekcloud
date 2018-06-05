package org.ranji.lemon.volador.service.personal.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.Student;
import org.ranji.lemon.volador.service.personal.prototype.IStudentService;
import org.springframework.stereotype.Service;
/**
 * 学员service实现类
 * @author 范小亚
 * @date 2018/6/4
 * @since JDK1.8
 * @version 1.0
 */
@Service("VoladorStudentServiceImpl")
public class StudentServiceImpl extends GenericServiceImpl<Student, Integer> implements IStudentService{

}
