package org.ranji.lemon.volador.service.pay.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.pay.Order;
import org.ranji.lemon.volador.persist.pay.prototype.IOrderDao;
import org.ranji.lemon.volador.service.pay.prototype.IOrderService;
import org.springframework.stereotype.Service;

@Service("VoladorPayOrderServiceImpl")
public class OrderServiceImpl extends GenericServiceImpl<Order, Integer> implements IOrderService {

	@Override
	public List<Order> findOrderByUserId(int userId) {
		// TODO Auto-generated method stub
		return ((IOrderDao) dao).findOrderByUserId(userId);
	}

}
