package org.ranji.lemon.volador.test.growthclass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.service.growthclass.prototype.IStageLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)
public class StageLabelServiceTest {
	@Autowired
	private IStageLabelService stageLabelService;
	
//	@Test
//	public void sava(){
//		StageLabel stageLabel = new StageLabel();
//		stageLabel.setLabel("images/wqf_goods1.png");
//		stageLabel.setTitle("立体化思维");
//		
//		stageLabelService.save(stageLabel);
//	}
//	@Test
//	public void find(){		
//		System.out.println(stageLabelService.findAll().toString());
//	}
//	@Test
//	public void delete(){
//		stageLabelService.delete(4);
//	}
	
	@Test
	public void function(){
//		stageLabelService.saveStageAndLabelRelation(1, 1);
//		stageLabelService.saveStageAndLabelRelation(1, 2);
//		stageLabelService.saveStageAndLabelRelation(1, 3);
//		stageLabelService.saveStageAndLabelRelation(2, 1);
//		stageLabelService.saveStageAndLabelRelation(2, 2);
//		stageLabelService.saveStageAndLabelRelation(2, 3);
		System.out.println(stageLabelService.findStageLabelByStageId(1).toString());
	}

}
