package org.ranji.lemon.volador.test.growthclass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.growthclass.LabelClassify;
import org.ranji.lemon.volador.service.growthclass.prototype.ILabelClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)
public class LabelClassifyServiceTest {
	@Autowired
	private ILabelClassifyService labelClassifyService;
//	@Test
//	public void save(){
//		LabelClassify labelClassify = new LabelClassify();
//		labelClassify.setClassify("面性思维");
//		labelClassifyService.save(labelClassify);
//		labelClassify.setClassify("立体化思维");
//		labelClassifyService.save(labelClassify);
//		labelClassify.setClassify("重点难点突破");
//		labelClassifyService.save(labelClassify);
//		labelClassify.setClassify("极限与方法");
//		labelClassifyService.save(labelClassify);
//		labelClassify.setClassify("思维应用");
//		labelClassifyService.save(labelClassify);
//	}
//	@Test
//	public void find(){		
//		System.out.println(labelClassifyService.findAll().toString());
//	}
	@Test
	public void function(){
//		labelClassifyService.saveLabelAndClassifyRelation(1, 1);
//		labelClassifyService.saveLabelAndClassifyRelation(1, 2);
//		labelClassifyService.saveLabelAndClassifyRelation(1, 3);
//		labelClassifyService.saveLabelAndClassifyRelation(1, 4);
//		labelClassifyService.saveLabelAndClassifyRelation(1, 5);
//		labelClassifyService.saveLabelAndClassifyRelation(2, 1);
//		labelClassifyService.saveLabelAndClassifyRelation(2, 2);
//		labelClassifyService.saveLabelAndClassifyRelation(2, 3);
//		labelClassifyService.saveLabelAndClassifyRelation(2, 4);
//		labelClassifyService.saveLabelAndClassifyRelation(2, 5);
		System.out.println(labelClassifyService.findLabelClassifyByLabelId(1).toString());
	}

}
