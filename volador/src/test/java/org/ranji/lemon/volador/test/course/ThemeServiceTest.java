package org.ranji.lemon.volador.test.course;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Theme;
import org.ranji.lemon.volador.service.course.prototype.IThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class ThemeServiceTest {
	@Autowired
	private IThemeService themeService;
	
//	@Test
//	public void addTheme(){
//		Theme theme = new Theme();
//		theme.setTitle("百万级人气教程免费学，1键get 1000+优质资源");
//		theme.setDescribe("独家研发，随时更新，流行技法一网打尽！");
//		themeService.save(theme);
//		System.out.print(theme.toString());
//	}
//	
	@Test
	public void addCourseAndTheme(){

		themeService.saveCourseAndThemeRelation(3, 1);
		themeService.saveCourseAndThemeRelation(3, 2);
		themeService.saveCourseAndThemeRelation(3, 3);
		themeService.saveCourseAndThemeRelation(4, 4);
		themeService.saveCourseAndThemeRelation(4, 5);
		System.out.print("保存课程分类表");
	}
	
//	@Test
//	public void CourseAndTheme(){
//		
//		themeService.deleteCourseAndThemeRelation(1, 1);
//		themeService.deleteCourseAndThemeAllRelationByThemdId(2);
//		themeService.deleteCourseAndThemeAllRelationByCourseId(3);
//		List<Course> CourstList = themeService.findCourseAndThemeRelationByCourseId(2);
//
//		System.out.print(CourstList.toString());
//	}
	
//	@Test
//	public void updateTheme(){
//		
//		themeService.delete(1);
//		System.out.print(themeService.findAll().toString());
//	}


}
