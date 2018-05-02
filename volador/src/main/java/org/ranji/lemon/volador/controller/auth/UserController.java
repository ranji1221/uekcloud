package org.ranji.lemon.volador.controller.auth;

import org.ranji.lemon.volador.service.auth.prototype.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		System.out.println("aaaaaaaaaaaaaa");
		mv.addObject("users", userService.findAll(null));
		mv.setViewName("/auth/user/list");
		return mv;
	}
}
