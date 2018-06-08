package org.ranji.lemon.volador.persist.pay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public List<Order> findPageOrderList(int page, int limit) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("offset", (page-1)*limit);
		params.put("limit", limit);
		return sqlSessionTemplate.selectList(typeNameSpace+".findPageOrderList", params);
	}

	@Override
	public int orderCount() {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String,Object>();
		return (int) sqlSessionTemplate.selectList(typeNameSpace+".orderCount",params).get(0);
	}
	
	
}
