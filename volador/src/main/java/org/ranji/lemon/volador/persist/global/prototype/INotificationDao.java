package org.ranji.lemon.volador.persist.global.prototype;

import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.model.global.Notification;

public interface INotificationDao extends IGenericDao<Notification, Integer> {
	/**
	 * 保存用户忽略公告/消息关系
	 * @param userId
	 * @param ignoreNotificationNumber
	 */
	public void saveUserAndIgnoreNotificationRelation(int userId,int ignoreNotificationNumber);
	
	/**
	 * 查询未读公告/消息前三条数据
	 * @param startIgnNotificationNumber
	 * @param endIgnNotificationNumber
	 * @return
	 */
	public List<Map> findTop3Notification(int startIgnNotificationNumber,int endIgnNotificationNumber);
	
	/**
	 * 根据用户id查询忽略消息的id
	 * @param userId
	 * @return
	 */
	public Map findIgnoreNotificationByUser(int userId);
	
	/**
	 * 查询公告总条数
	 * @return
	 */
	public int getTotalOfItems();
	
	/**
	 * 更新忽略消息表
	 * @param map
	 */
	public void updateIgnoreNotNum(Map map);
	
	/**
	 * 查询未读的消息总数
	 * @param startIgnNotificationNumber
	 * @param endIgnNotificationNumber
	 * @return
	 */
	public int notReadNumber(int startIgnNotificationNumber,int endIgnNotificationNumber );
	
	/**
	 * 查询最后一条公告/消息的id
	 * @return
	 */
	public int maxNotificationId();
	
	/**
	 * 分页查询消息总数
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Notification> findNotificationInfoByPage(int page,int limit);
	
}
