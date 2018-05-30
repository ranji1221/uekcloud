package org.ranji.lemon.volador.service.global.impl;

import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.global.Notification;
import org.ranji.lemon.volador.persist.global.prototype.INotificationDao;
import org.ranji.lemon.volador.service.global.prototype.INotificationService;
import org.springframework.stereotype.Service;
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
		return ((INotificationDao)dao).getTotalOfItems();
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
    
	
}
