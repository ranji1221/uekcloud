package org.ranji.lemon.volador.test.course;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.service.course.prototype.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class TeacherDaoTest {
	@Autowired
	private ITeacherService teacherService;
	@Test
	public void findHeaderTeacher(){
		//获取首页推荐老师
		List<Teacher> teacherList = teacherService.findHeaderTeacher();
		System.out.print(teacherList.toString());
	}

}
