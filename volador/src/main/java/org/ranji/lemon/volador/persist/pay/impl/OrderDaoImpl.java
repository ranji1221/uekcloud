package org.ranji.lemon.volador.persist.pay.impl;

import java.util.List;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.pay.Order;
import org.ranji.lemon.volador.persist.pay.prototype.IOrderDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorPayOrderDaoImpl")
public class OrderDaoImpl extends GenericDaoImpl<Order, Integer> implements IOrderDao {

	@Override
	public List<Order> findOrderByUserId(int userId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace+".findOrderByUserId", userId);
		
	}
	
	
}
