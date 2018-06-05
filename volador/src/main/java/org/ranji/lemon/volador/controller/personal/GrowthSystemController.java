package org.ranji.lemon.volador.controller.personal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.ranji.lemon.volador.model.personal.GrowthClass;
import org.ranji.lemon.volador.model.personal.GrowthStage;
import org.ranji.lemon.volador.model.personal.Integral;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.service.personal.prototype.IGrowthClassService;
import org.ranji.lemon.volador.service.personal.prototype.IIntegralService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.ISignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GrowthSystemController {
	@Autowired
	private IPerService personalService;
	@Autowired
	private IGrowthClassService growthClassService;
	@Autowired
	private ISignInService signInService;
	
	@Autowired
	private IIntegralService integralService;
	
	@RequestMapping(value="/growth_system", method=RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		try{
			//根据session获取userId，查询正在学习课程
			int userId=(int) request.getSession().getAttribute("userId");
			
			//查询当前用户信息
			UserInfo userInfo=personalService.findUserInfoByUserId(userId);
			mv.addObject("user_name",userInfo.getNickname());
			mv.addObject("gender", userInfo.getGender());
			mv.addObject("address",userInfo.getAddress());
			
			//查询学习时长
			
			
			//查询我的积分
			Integral integral = integralService.findIntegralByUserId(userId);
			mv.addObject("integralNum", integral.getIntegralNumber());
			
			//查询签到天数
			SignIn signIn = signInService.findSignInByUserId(userId);
			mv.addObject("siginDay", signIn.getDay());
			
		}catch(NullPointerException e){
			e.printStackTrace();
		}
								
		//获取职业导航
		List<GrowthClass> growthClassList = growthClassService.findAll();
		mv.addObject("growthClassCount", "全部职业导航 "+String.valueOf(growthClassList.size()));
		
		//职业导航页展示导航列表
		/*List<GrowthSystemShow> growthSystemShowList = new ArrayList<GrowthSystemShow>();
		//循环获取职业导航及其对应的阶段
		for(GrowthClass growthClass:growthClassList){
			GrowthSystemShow growthSystemShow = new GrowthSystemShow();
			growthSystemShow.setGrowthClass(growthClass);
			
			List<GrowthStage> growthStageList = growthClassService.findGrowthStageByGrowthClassId(growthClass.getId());
			
			//查询用户学习阶段下正在学习的章节
			
			
			growthSystemShow.setGrowthStageList(growthStageList);
			
			growthSystemShowList.add(growthSystemShow);
		}
		mv.addObject(growthSystemShowList);*/
		
		mv.setViewName("/backend/wzq_zhiye");
		return mv;
	}

}
