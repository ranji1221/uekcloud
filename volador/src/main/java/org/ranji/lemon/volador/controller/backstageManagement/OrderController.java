package org.ranji.lemon.volador.controller.backstageManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.service.pay.prototype.IOrderService;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrderController {

	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IAdminService adminService;
	
	@RequestMapping(value="/order",method=RequestMethod.GET)
	public void orderList(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		PrintWriter pw=response.getWriter();
		Map result = new HashMap<>();
		if(adminService.parseJWT(request.getHeader("token"))){
			int page=Integer.parseInt(request.getParameter("page"));
			int limit=Integer.parseInt(request.getParameter("limit"));
			Map orderPage=orderService.pageOrder(page,limit);
			int code=Integer.parseInt(orderPage.get("code").toString());
			if(code==200){
				result.put("code", 200);
				result.put("message", "获取成功");
				result.put("page", orderPage.get("page"));
				Map data=new HashMap<>();
				data.put("orderList", orderPage.get("orderList"));
				result.put("data", data);
			}else{
				result.put("code", "404");
				result.put("message", "获取失败");
			}
		}else{
			result.put("code", "404");
			result.put("message", "非法请求");
				
		}
		pw.print(JsonUtil.objectToJson(result));
		pw.flush();
		pw.close();

		
	}
}
