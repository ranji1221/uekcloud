package org.ranji.lemon.volador.test.Personal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.core.util.DateUtil;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.course.Carouse;
import org.ranji.lemon.volador.model.personal.Student;
import org.ranji.lemon.volador.service.course.prototype.ICarouseService;
import org.ranji.lemon.volador.service.personal.prototype.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class StudentDaoTest {

	@Autowired
	private IStudentService studentService;
	
	@Test
	public void functionTest(){
		Student student = new Student();
		student.setId(3);
//		student.setName("吴同学");
//		student.setCompany("北京某教育咨询公司");
//		student.setPosition("UI设计师");
//		student.setTitle("图标的可用性测试，测的是什么？");
//		student.setDescription("在业务迭代周期内，产品经理和设计师对要做的产品需求和功能点进行需求分析和设计讨论的过程中，有时会出现一些拿捏不定的设计细节。");
		student.setImage("student_wu.jpg");
		studentService.update(student);
		student.setId(1);
		student.setImage("student_hao.jpg");
		studentService.update(student);
	}
}
