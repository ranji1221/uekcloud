package org.ranji.lemon.volador.service.personal.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.persist.personal.prototype.IPerDao;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
/**
 * UserService实现类
 * @author 范小亚
 * @since JDK1.8
 * @date 2018/5/8
 * @version 1.0
 *
 */
@Service("VoladorPerUserServiceImpl")
public class PerServiceImpl extends GenericServiceImpl<Per, Integer> implements IPerService{

	
	@Override
	public UserInfo findUserInfoByUserId(int userId) {
		List <UserInfo> userInfoList = ((IPerDao) dao).findUserInfoByUserId(userId);
		return userInfoList.get(0);
	}

	public void save(Per entity) {
		entity.setPassword(new DefaultPasswordService().encryptPassword(entity.getPassword()));
		super.save(entity);
	}
	
	@Override
	public Per findByUserName(String userName) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("username", userName);
		List<Per> users = dao.findAll(params);
		Per user = null;
		if(null != users && 0 < users.size())
			user = users.get(0);
		return user;
	}

	@Override
	public void saveUserAndUserInfoRelation(int userId, int userinfoId) {
		((IPerDao) dao).saveUserAndUserInfoRelation(userId, userinfoId);
		
	}

	@Override
	public void deleteUserAndUserInfoRelation(int userId, int userinfoId) {
		((IPerDao) dao).deleteUserAndUserInfoRelation(userId, userinfoId);		
	}

	@Override
	public void deleteUserUserInfoByUserId(int userId) {
		((IPerDao) dao).deleteUserAndUserInfoRelationByUserId(userId);
		
	}

	@Override
	public List<Integer> findUserUserInfoRelationByUserId(int userId) {
		return ((IPerDao) dao).findUserUserInfoRelationByUserId(userId);
	}

	@Override
	public void saveUserAndBuyCourseRelation(int userId, int courseId) {
		((IPerDao) dao).saveUserAndBuyCourseRelation(userId, courseId);		
	}

	@Override
	public List<Integer> findBuyCourseRelationByUserId(int userId) {
		return ((IPerDao) dao).findBuyCourseRelationByUserId(userId);
	}

	@Override
	public void deleteBuyCourseRelationByUserId(int userId) {
		((IPerDao) dao).deleteBuyCourseRelationByUserId(userId);
	}

	@Override
	public void deleteBuyCourseRelation(int userId, int courseId) {
		((IPerDao) dao).deleteBuyCourseRelation(userId, courseId);
	}

	@Override
	public void saveUserAndCollectCourseRelation(int userId, int courseId) {
		((IPerDao) dao).saveUserAndCollectCourseRelation(userId, courseId);		
	}

	@Override
	public List<Integer> findCollectCourseRelationByUserId(int userId) {
		return ((IPerDao) dao).findCollectCourseRelationByUserId(userId);
	}

	@Override
	public void deleteCollectCourseRelationByUserId(int userId) {
		((IPerDao) dao).deleteCollectCourseRelationByUserId(userId);
	}

	@Override
	public void deleteCollectCourseRelation(int userId, int courseId) {
		((IPerDao) dao).deleteCollectCourseRelation(userId, courseId);
	}
	
	@Override
	public void saveUserAndStudyingCourseRelation(int userId, int courseId) {
		((IPerDao) dao).saveUserAndStudyingCourseRelation(userId, courseId);		
	}

	@Override
	public List<Integer> findStudyingCourseRelationByUserId(int userId) {
		return ((IPerDao) dao).findStudyingCourseRelationByUserId(userId);
	}

	@Override
	public void deleteStudyingCourseRelationByUserId(int userId) {
		((IPerDao) dao).deleteStudyingCourseRelationByUserId(userId);
	}

	@Override
	public void deleteStudyingCourseRelation(int userId, int courseId) {
		((IPerDao) dao).deleteStudyingCourseRelation(userId, courseId);
	}
	
	@Override
	public void saveUserAndStudyedCourseRelation(int userId, int courseId) {
		((IPerDao) dao).saveUserAndStudyedCourseRelation(userId, courseId);		
	}

	@Override
	public List<Integer> findStudyedCourseRelationByUserId(int userId) {
		return ((IPerDao) dao).findStudyedCourseRelationByUserId(userId);
	}

	@Override
	public void deleteStudyedCourseRelationByUserId(int userId) {
		((IPerDao) dao).deleteStudyedCourseRelationByUserId(userId);
	}

	@Override
	public void deleteStudyedCourseRelation(int userId, int courseId) {
		((IPerDao) dao).deleteStudyedCourseRelation(userId, courseId);
	}

	@Override
	public void saveUserAndHomeworkRelation(int userId, int homeworkId) {
		((IPerDao) dao).saveUserAndHomeworkRelation(userId, homeworkId);	
	}

	@Override
	public List<Integer> findHomeworkRelationByUserId(int userId) {
		return ((IPerDao) dao).findHomeworkRelationByUserId(userId);
	}

	@Override
	public void deleteHomeworkRelationByUserId(int userId) {
		((IPerDao) dao).deleteHomeworkRelationByUserId(userId);	
	}

	@Override
	public void deleteHomeworkRelation(int userId, int homeworkId) {
		((IPerDao) dao).deleteHomeworkRelation(userId, homeworkId);	
	}	
}
