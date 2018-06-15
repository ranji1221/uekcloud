package org.ranji.lemon.volador.service.global.impl;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.model.global.Notification;
import org.ranji.lemon.volador.persist.global.prototype.INotificationDao;
import org.ranji.lemon.volador.service.global.prototype.INotificationService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Service("VoladorNotificationServiceImpl")
public class NotificationServiceImpl extends GenericServiceImpl<Notification, Integer> implements INotificationService{

	@Override
	public void saveUserAndIgnoreNotificationRelation(int userId, int ignoreNotificationNumber) {
		// TODO Auto-generated method stub
		((INotificationDao)dao).saveUserAndIgnoreNotificationRelation(userId,ignoreNotificationNumber);
	}

	@Override
	public List<Map> findTop3Notification(int startIgnNotificationNumber, int endIgnNotificationNumber) {
		// TODO Auto-generated method stub
		return ((INotificationDao)dao).findTop3Notification(startIgnNotificationNumber, endIgnNotificationNumber);
	}

	@Override
	public Map findIgnoreNotificationByUser(int userId) {
		// TODO Auto-generated method stub
		return ((INotificationDao)dao).findIgnoreNotificationByUser(userId);
	}

	@Override
	public int getTotalOfItems() {
		// TODO Auto-generated method stub
		//判空
		List<Notification> notificationList = ((INotificationDao)dao).findAll();
		if(0 != notificationList.size()){
			return ((INotificationDao)dao).getTotalOfItems();
		}else{
			return 0;
		}
	}

	@Override
	public void updateIgnoreNotNum(Map map) {
		// TODO Auto-generated method stub
		((INotificationDao)dao).updateIgnoreNotNum(map);
	}
	
	@Override
	public int notReadNumber(int startIgnNotificationNumber,int endIgnNotificationNumber ){
		return ((INotificationDao)dao).notReadNumber(startIgnNotificationNumber, endIgnNotificationNumber);
	}

	@Override
	public int maxNotificationId() {
		// TODO Auto-generated method stub
		return ((INotificationDao)dao).maxNotificationId();
	}

	@Override
	public Map notificationListByPage(int page, int limit) {
		// TODO Auto-generated method stub
		Map resultMap = new HashMap<>();
		List<Notification> notificationList = ((INotificationDao)dao).findNotificationInfoByPage(page, limit);
		int notificationCount = ((INotificationDao)dao).getTotalOfItems();
		int pageCount = 0;
		Map listMap = new HashMap<>();
		listMap.put("notificationList", notificationList);
		resultMap.put("data", listMap);
		resultMap.put("totalCount", notificationCount);
		if(notificationCount%limit==0){
			pageCount = notificationCount/limit;
		}else{
			pageCount = notificationCount/limit+1;
		}
		resultMap.put("pageCount", pageCount);
		resultMap.put("pageNo", page);
		return resultMap;
	}
    
	
}
