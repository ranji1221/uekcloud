package org.ranji.lemon.volador.service.pay.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.pay.Order;
import org.ranji.lemon.volador.model.pay.VoladorCode;
import org.ranji.lemon.volador.persist.course.prototype.ICourseDao;
import org.ranji.lemon.volador.persist.pay.prototype.IOrderDao;
import org.ranji.lemon.volador.service.pay.prototype.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("VoladorPayOrderServiceImpl")
public class OrderServiceImpl extends GenericServiceImpl<Order, Integer> implements IOrderService {

	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private ICourseDao courseDao;
	
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

	@Override
	public void saveVoladorCode(int courseId) {
		// TODO Auto-generated method stub
		String voladorCode=UUID.randomUUID().toString().replaceAll("-", "");
		orderDao.saveVoladorCode(courseId, voladorCode);
		
	}

	@Override
	public void useVoladorCode(String voladorCode) {
		// TODO Auto-generated method stub
		orderDao.useVoladorCode(voladorCode);
		
	}

	@Override
	public VoladorCode findVoladorCode(String voladorCode) {
		// TODO Auto-generated method stub
		return orderDao.findVoladorCode(voladorCode);
	}

	@Override
	public List<VoladorCode> findVoladorCodeAll() {
		// TODO Auto-generated method stub
		return orderDao.findVoladorCodeAll();
	}

	@Override
	public Map<String,Object> pageVoladorCode(int page, int limit, int status) {
		Map<String,Object> result=new HashMap<String,Object>();
		if (status==0){
			List<VoladorCode> voladorCodeList=orderDao.findPageVoladorCodeAll(page, limit);
			Map<String,Object> pageMap=new HashMap<String,Object>();
			int totalCount=orderDao.findVoladorCodeCount();
			pageMap.put("totalCount", totalCount);
			pageMap.put("pageNo", page);
			if(totalCount%limit==0){
				pageMap.put("pageCount", totalCount/limit);
			}else{
				pageMap.put("pageCount", totalCount/limit+1);
			}
			
			List<Map> voladorCodeListMap=new ArrayList<Map>();
			for(VoladorCode voladorCode:voladorCodeList){
				Map<String,Object> voladorCodeMap=new HashMap<String,Object>();
				try {
					voladorCodeMap.put("courseName", courseDao.find(voladorCode.getCourseId()).getCourse_name());
				} catch (Exception e) {
					// TODO: handle exception
					voladorCodeMap.put("courseName", "该课程已被删除");
				}
				voladorCodeMap.put("voladorCode", voladorCode.getVoladorCode());
				voladorCodeMap.put("status", voladorCode.getStatus());
				voladorCodeListMap.add(voladorCodeMap);
			}
			result.put("page", pageMap);
			result.put("voladorCode", voladorCodeListMap);
		}else{
			List<VoladorCode> voladorCodeList=orderDao.findPageVoladorCode(page, limit, status);
			Map<String,Object> pageMap=new HashMap<String,Object>();
			int totalCount=orderDao.findPageVoladorCodeCount(status);
			pageMap.put("totalCount", totalCount);
			pageMap.put("pageNo", page);
			if(totalCount%limit==0){
				pageMap.put("pageCount", totalCount/limit);
			}else{
				pageMap.put("pageCount", totalCount/limit+1);
			}
			List<Map> voladorCodeListMap=new ArrayList<Map>();
			for(VoladorCode voladorCode:voladorCodeList){
				Map<String,Object> voladorCodeMap=new HashMap<String,Object>();
				try {
					voladorCodeMap.put("courseName", courseDao.find(voladorCode.getCourseId()).getCourse_name());
				} catch (Exception e) {
					// TODO: handle exception
					voladorCodeMap.put("courseName", "该课程已被删除");
				}
				voladorCodeMap.put("voladorCode", voladorCode.getVoladorCode());
				voladorCodeMap.put("status", voladorCode.getStatus());
				voladorCodeListMap.add(voladorCodeMap);
			}
			result.put("page", pageMap);
			result.put("voladorCode", voladorCodeListMap);
		}
		
		
		// TODO Auto-generated method stub
		return result;
	}

	@Override
	public List<VoladorCode> findPageVoladorCodeAll(int page, int limit) {
		// TODO Auto-generated method stub
		return orderDao.findPageVoladorCodeAll(page, limit);
	}

	@Override
	public List<VoladorCode> findPageVoladorCode(int page, int limit, int status) {
		// TODO Auto-generated method stub
		return orderDao.findPageVoladorCode(page, limit, status);
	}

}
