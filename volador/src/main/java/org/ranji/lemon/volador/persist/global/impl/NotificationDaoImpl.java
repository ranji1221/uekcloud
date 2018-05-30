package org.ranji.lemon.volador.persist.global.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.RespectBinding;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.global.Notification;
import org.ranji.lemon.volador.persist.global.prototype.INotificationDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorNotificationDaoImpl")
public class NotificationDaoImpl extends GenericDaoImpl<Notification, Integer> implements INotificationDao{
	@Override
	public void saveUserAndIgnoreNotificationRelation(int userId,int ignoreNotificationNumber){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userId);
		map.put("ignoreNotificationNumber", ignoreNotificationNumber);
		sqlSessionTemplate.insert(typeNameSpace+".saveUserAndIgnoreNotificationRelation", map);
		
	}

	@Override
	public List<Map> findTop3Notification(int startIgnNotificationNumber, int endIgnNotificationNumber) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startIgnNotificationNumber", startIgnNotificationNumber);
		map.put("endIgnNotificationNumber", endIgnNotificationNumber);
		return sqlSessionTemplate.selectList(typeNameSpace+".findTop3Notification", map);
	}

	@Override
	public Map findIgnoreNotificationByUser(int userId) {
		// TODO Auto-generated method stub
	 return sqlSessionTemplate.selectOne(typeNameSpace+".findIgnoreNotificationByUser", userId);
	}

	@Override
	public int getTotalOfItems() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace+".getTotalOfItems");
	}

	@Override
	public void updateIgnoreNotNum(Map map) {
		// TODO Auto-generated method stub
		sqlSessionTemplate.update(typeNameSpace+".updateIgnoreNotNum",map);
	}

	@Override
	public int notReadNumber(int startIgnNotificationNumber, int endIgnNotificationNumber) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("startIgnNotificationNumber", startIgnNotificationNumber);
		map.put("endIgnNotificationNumber", endIgnNotificationNumber);
		return sqlSessionTemplate.selectOne(typeNameSpace+".notReadNumber",map);
	}
	
	
	
}
