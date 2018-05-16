package org.ranji.lemon.volador.test.course;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.course.Homework;
import org.ranji.lemon.volador.service.course.prototype.IHomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class HomeworkDaoTest {
	@Autowired
	private IHomeworkService homeworkservice;
//	@Test
//    public void add(){
//    	Homework homework = new Homework();
//    	homework.setAddress("E:\\Fish2");
//    	homework.setInfo("作业内容");
//    	homework.setName("作业名字");
//    	homework.setType("艺术类");    	
//    	homeworkservice.save(homework);
//    	System.out.println("添加作业：/n"+homework.toString());
//    }
//	@Test
//    public void update(){
//    	List<Homework> homeworks = homeworkservice.findAll();
//    	Homework homework = homeworks.get(0);
//    	homework.setAddress("更新之后E:\\Fish");
//    	homework.setInfo("更新之后作业内容");
//    	homework.setName("更新之后作业名字");
//    	homework.setType("更新之后艺术类");
//    	homework.setUpdateTime(new Date());
//    	homeworkservice.update(homework);
//    	System.out.println("更新作业：/n");
//    }
//	
	@Test
    public void delete(){
    	List<Homework> homeworks = homeworkservice.findAll();
    	Homework homework = homeworks.get(0);
    	homeworkservice.delete(homework.getId());
    	System.out.println("删除作业：/n");
    }
}
