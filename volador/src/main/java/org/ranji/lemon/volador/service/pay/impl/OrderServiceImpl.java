package org.ranji.lemon.volador.service.pay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.pay.Order;
import org.ranji.lemon.volador.persist.pay.prototype.IOrderDao;
import org.ranji.lemon.volador.service.pay.prototype.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VoladorPayOrderServiceImpl")
public class OrderServiceImpl extends GenericServiceImpl<Order, Integer> implements IOrderService {

	@Autowired
	private IOrderDao orderDao;
	
	@Override
	public List<Order> findOrderByUserId(int userId) {
		// TODO Auto-generated method stub
		return ((IOrderDao) dao).findOrderByUserId(userId);
	}

	@Override
	public Map<String, Object> pageOrder(int page, int limit) {
		// TODO Auto-generated method stub
		Map<String,Object> result=new HashMap<String,Object>();
		try {
			List<Order> orderList=orderDao.findPageOrderList(page,limit);
			Map pageMap=new HashMap<>();
			int orderCount=orderDao.orderCount();
			int pageCount;
			if((orderCount%limit==0)){
				pageCount=orderCount%limit;
			}else{
				pageCount=(orderCount/limit)+1;
			}
			pageMap.put("pageCount", pageCount);
			pageMap.put("pageNo", page);
			pageMap.put("totalCount", orderCount);
			result.put("page", pageMap);
			result.put("orderList", orderList);
			result.put("code", 200);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			result.put("code", 404);
		}
		return result;
	}

}
