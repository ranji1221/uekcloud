package org.ranji.lemon.volador.test.course;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.core.util.DateUtil;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.course.Carouse;
import org.ranji.lemon.volador.service.course.prototype.ICarouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class CarouseDaoTest {

	@Autowired
	private ICarouseService carouseService;
	
	@Test
	public void functionTest(){
		Carouse carouse = new Carouse();
		carouse.setTitle("PYTHON涉及前景如何");
		carouse.setDescription("PYTHON人工智能浪潮汹涌而来。");
		carouse.setImage("images/wqf_banner.png");
		carouse.setChainedAddress("http://www.ui029.com/");
		carouse.setShowTime(DateUtil.now());
		carouseService.save(carouse);
//		Carouse carouse = carouseService.find(1);
//		carouse.setUpdateTime(DateUtil.now());
//		carouseService.update(carouse);
		//System.out.println(carouseService.find(1).setUpdateTime(DateUtil.now()));
//		carouseService.delete(2);
	}
}
