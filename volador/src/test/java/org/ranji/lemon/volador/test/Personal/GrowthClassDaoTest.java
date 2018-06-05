package org.ranji.lemon.volador.test.Personal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.personal.GrowthClass;
import org.ranji.lemon.volador.service.personal.prototype.IGrowthClassService;
import org.ranji.lemon.volador.service.personal.prototype.IGrowthStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class GrowthClassDaoTest {

	@Autowired
	private IGrowthClassService growthClassService;
	@Autowired
	private IGrowthStageService growthStageService;
	
//	@Test
//	public void save(){
//		GrowthClass growthClass = new GrowthClass();
//		growthClass.setTitle("Python进阶之路");
//		growthClass.setSend_word("距离成为一个Python工程师，你还差了50%的距离");
//		growthClass.setDescription("树立Python工程师，了解Python发展规律及就业前景。");
//		growthClassService.save(growthClass);
//	}
	@Test
	public void functionTest(){
//		System.out.println(growthClassService.findAll().toString());
//		GrowthClass growthClass = growthClassService.find(2);
//		growthClass.setDescription("树立Python工程师，了解Python发展规律及就业前景，努力向人工智能发展。");
//		growthClassService.update(growthClass);
//		growthClassService.saveGrowthClassAndStageRelation(1, 1);
//		growthClassService.saveGrowthClassAndStageRelation(1, 2);
//		growthClassService.saveGrowthClassAndStageRelation(1, 3);
//		growthClassService.saveGrowthClassAndStageRelation(2, 1);
//		System.out.println(growthClassService.findGrowthClassByGrowthStageId(1).toString());
//		System.out.println(growthClassService.findGrowthStageByGrowthClassId(1).toString());
//		growthClassService.deleteGrowthClassAndStageRelationByClassId(1);
//		growthClassService.deleteGrowthClassAndStageRelation(1, 1);
	}
}
