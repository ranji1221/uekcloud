package org.ranji.lemon.volador.service.global.prototype;

import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.global.Notification;

public interface INotificationService extends IGenericService<Notification, Integer>{

	public void saveUserAndIgnoreNotificationRelation(int userId,int ignoreNotificationNumber);
	
	public List<Map> findTop3Notification(int startIgnNotificationNumber,int endIgnNotificationNumber);
	
	public Map findIgnoreNotificationByUser(int userId);
	
	public int getTotalOfItems();
	
	public void updateIgnoreNotNum(Map map);
	
	public int notReadNumber(int startIgnNotificationNumber,int endIgnNotificationNumber );

}
