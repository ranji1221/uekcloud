package org.ranji.lemon.volador.persist.global.prototype;

import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.global.Notification;

public interface INotificationDao extends IGenericDao<Notification, Integer> {

	public void saveUserAndIgnoreNotificationRelation(int userId,int ignoreNotificationNumber);
	
	public List<Map> findTop3Notification(int startIgnNotificationNumber,int endIgnNotificationNumber);
	
	public Map findIgnoreNotificationByUser(int userId);
	
	public int getTotalOfItems();
}
