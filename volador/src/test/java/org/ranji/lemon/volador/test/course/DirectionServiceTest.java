package org.ranji.lemon.volador.test.course;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.course.Classify;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Direction;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.course.prototype.IDirectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class DirectionServiceTest {
	@Autowired
	private IDirectionService directionService;
	@Autowired
	private IClassifyService classifyService;
	@Autowired
	private ICourseService courseService;
	
//	@Test
//	public void findClassifyByDirectionId(){
//		directionService.deleteDirectionAndClassifyRelationByDirectionId(1);
//		directionService.deleteDirectionAndClassifyRelation(2, 2);
//		//System.out.println(courseList.toString());
//	}
	@Test
	public void findClassifyByDirectionId(){
//		List<Classify> classifyList = directionService.findClassifyByDirectionId(3);
//		List<Course> courseList = directionService.findCourseByDirectionId(3);
		System.out.println(directionService.findAll().toString());
	}
	
//	@Test
//	public void saveDirectionAndClassifyRelation(){
//		Direction direction = new Direction();
//		Classify classify = new Classify();
//
//
//		direction.setName("UI/UX设计");
//		directionService.save(direction);		
//		classify.setClassify_name("UI/UX设计");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("软件基础");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("sketch");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("Axure");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("用户体验");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());				
//		
//		direction.setName("前端开发");
//		directionService.save(direction);
//		classify.setClassify_name("界面重构");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("JavaScript");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("jQuery");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("PHP");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("框架");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		
//		direction.setName("Python全栈");
//		directionService.save(direction);
//		classify.setClassify_name("界面重构");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("JavaScript");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("Python");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("框架");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		
//		direction.setName("人工智能");
//		directionService.save(direction);
//		classify.setClassify_name("机器学习");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("深度学习");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		
//		direction.setName("大数据");
//		directionService.save(direction);
//		classify.setClassify_name("大数据");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("云计算");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		
//		direction.setName("大咖来了");
//		directionService.save(direction);
//		classify.setClassify_name("UI大咖");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("Python大咖");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("UI大咖");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//		classify.setClassify_name("前端大咖");
//		classifyService.save(classify);
//		directionService.saveDirectionAndClassifyRelation(direction.getId(), classify.getId());
//	}

}
