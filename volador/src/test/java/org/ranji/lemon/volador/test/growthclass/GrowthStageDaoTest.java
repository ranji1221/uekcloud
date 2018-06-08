package org.ranji.lemon.volador.test.growthclass;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthClassService;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class GrowthStageDaoTest {

	@Autowired
	private IGrowthClassService growthClassService;
	@Autowired
	private IGrowthStageService growthStageService;
	
	
//	@Test
//	public void save(){
//		GrowthStage growthStage = new GrowthStage();
//		growthStage.setTitle("视觉设计进阶篇");
//		growthStage.setDescription("掌握UI设计工具的使用后，通过学习素描光影关系、色彩原理与搭配及构图原理，打好成为UI设计师的美术造型基础和树立色彩审美意识");
//		growthStageService.save(growthStage);
//		growthStage.setTitle("视觉设计高级篇");
//		growthStage.setDescription("成为UI设计师");
//		growthStageService.save(growthStage);
//		System.out.println(growthStage.toString());
//	}
	@Test
	public void functionTest(){
//		GrowthStage growthStage = growthStageService.find(3);
//		growthStage.setDescription("成为UI大牛");
//		growthStageService.update(growthStage);
//		System.out.println(growthStageService.findAll());
//		growthStageService.delete(4);
//		growthStageService.saveGrowthStageAndCourseRelation(1, 6);
//		growthStageService.saveGrowthStageAndCourseRelation(2, 7);
//		growthStageService.saveGrowthStageAndCourseRelation(2, 8);
//		growthStageService.saveGrowthStageAndCourseRelation(3, 9);
//		growthStageService.saveGrowthStageAndCourseRelation(3, 10);
//		growthStageService.saveGrowthStageAndCourseRelation(3, 11);
		System.out.println(growthStageService.findAll());
//		growthStageService.deleteGrowthStageAndCourseRelationByStageId(3);
//		growthStageService.deleteGrowthStageAndCourseRelation(2, 8);
	}
}
