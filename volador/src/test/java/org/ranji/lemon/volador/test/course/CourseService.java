package org.ranji.lemon.volador.test.course;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.service.auth.prototype.IUserService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class CourseService {

	@Autowired
	private ICourseService courseService;
//	
//	@Test
//	public void testSaveCourseAndTeacherRelation(){
//		courseService.saveCourseAndTeacherRelation(4, 12);
//	}
//	
//	@Test
//	public void testDelateCourseAndTeacherRelation(){
//		courseService.deleteCourseAndTeacherRelation(1, 2);
//	}
//	
	@Test
	public void testFindTeacherbyCourse(){
//		List<Teacher> listTeacher;
//		listTeacher=courseService.findTeacherbyCourse(3);
//		courseService.deleteCourseAndChapterRelation(1, 1);
//		courseService.saveCourseAndChapterRelation(2, 1);
		
		List<Chapter> chapterlist=courseService.findChapterbyCourse(2);
		System.out.println(chapterlist.get(0).getChapter_name());
//		System.out.println(listTeacher.get(0).getTeacher_name());
	}

}
