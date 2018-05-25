package org.ranji.lemon.volador.persist.personal.impl;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.persist.personal.prototype.IPerDao;
@Repository("VoladorPerUserDaoImpl")
public class PerDaoImpl extends GenericDaoImpl<Per, Integer> implements IPerDao{

	@Override
	public void saveUserAndUserInfoRelation(int userId, int userinfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("userinfoId", userinfoId);
		sqlSessionTemplate.insert(typeNameSpace+".saveUserAndUserInfoRelation",params);
	}

	@Override
	public void deleteUserAndUserInfoRelation(int userId, int userinfoId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("userinfoId", userinfoId);
		sqlSessionTemplate.delete(typeNameSpace+".deleteUserAndUserInfoRelation", params);				
	}

	@Override
	public void deleteUserAndUserInfoRelationByUserId(int userId) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteUserAndUserInfoRelationByUserId", userId);		
	}

	@Override
	public List<Integer> findUserUserInfoRelationByUserId(int userId) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findUserUserInfoRelationByUserId", userId);
	}

	@Override
	public List<UserInfo> findUserInfoByUserId(int userId) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findUserInfoByUserId", userId);
	}

	@Override
	public void saveUserAndBuyCourseRelation(int userId, int courseId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("courseId", courseId);
		sqlSessionTemplate.insert(typeNameSpace+".saveUserAndBuyCourseRelation",params);
	}

	@Override
	public List<Integer> findBuyCourseRelationByUserId(int userId) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findBuyCourseRelationByUserId", userId);
	}
	
	@Override
	public void deleteBuyCourseRelationByUserId(int userId) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteBuyCourseRelationByUserId", userId);		
	}

	@Override
	public void deleteBuyCourseRelation(int userId, int courseId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("courseId", courseId);
		sqlSessionTemplate.delete(typeNameSpace+".deleteBuyCourseRelation", params);	
	}
	
	@Override
	public void saveUserAndCollectCourseRelation(int userId, int courseId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("courseId", courseId);
		sqlSessionTemplate.insert(typeNameSpace+".saveUserAndCollectCourseRelation",params);
	}

	@Override
	public List<Integer> findCollectCourseRelationByUserId(int userId) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findCollectCourseRelationByUserId", userId);
	}
	
	@Override
	public void deleteCollectCourseRelationByUserId(int userId) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteCollectCourseRelationByUserId", userId);		
	}

	@Override
	public void deleteCollectCourseRelation(int userId, int courseId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("courseId", courseId);
		sqlSessionTemplate.delete(typeNameSpace+".deleteCollectCourseRelation", params);	
	}
	
	@Override
	public void saveUserAndStudyingCourseRelation(int userId, int courseId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("courseId", courseId);
		sqlSessionTemplate.insert(typeNameSpace+".saveUserAndStudyingCourseRelation",params);
	}

	@Override
	public List<Integer> findStudyingCourseRelationByUserId(int userId) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findStudyingCourseRelationByUserId", userId);
	}
	
	@Override
	public void deleteStudyingCourseRelationByUserId(int userId) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteStudyingCourseRelationByUserId", userId);		
	}

	@Override
	public void deleteStudyingCourseRelation(int userId, int courseId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("courseId", courseId);
		sqlSessionTemplate.delete(typeNameSpace+".deleteStudyingCourseRelation", params);	
	}
	
	@Override
	public void saveUserAndStudyedCourseRelation(int userId, int courseId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("courseId", courseId);
		sqlSessionTemplate.insert(typeNameSpace+".saveUserAndStudyedCourseRelation",params);
	}

	@Override
	public List<Integer> findStudyedCourseRelationByUserId(int userId) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findStudyedCourseRelationByUserId", userId);
	}
	
	@Override
	public void deleteStudyedCourseRelationByUserId(int userId) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteStudyedCourseRelationByUserId", userId);		
	}

	@Override
	public void deleteStudyedCourseRelation(int userId, int courseId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", userId);
		params.put("courseId", courseId);
		sqlSessionTemplate.delete(typeNameSpace+".deleteStudyedCourseRelation", params);	
	}

	@Override
	public void saveUserAndHomeworkRelation(int userId, int homeworkId) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("homeworkId", homeworkId);
		sqlSessionTemplate.insert(typeNameSpace+".saveUserAndHomeworkRelation", params);		
	}

	@Override
	public List<Integer> findHomeworkRelationByUserId(int userId) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findHomeworkRelationByUserId", userId);
	}

	@Override
	public void deleteHomeworkRelationByUserId(int userId) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteHomeworkRelationByUserId", userId);	
	}

	@Override
	public void deleteHomeworkRelation(int userId, int homeworkId) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("homeworkId", homeworkId);
		sqlSessionTemplate.insert(typeNameSpace+".deleteHomeworkRelation", params);
	}

	@Override
	public UserInfo findUserInfoByNoteId(int noteId) {
		
		//根据笔记ID查找用户
		List<Per> personList = sqlSessionTemplate.selectList(typeNameSpace+".findUserByNoteId", noteId);
		
		int userId = personList.get(0).getId();
		
		//根据用户ID查找用户信息
		List<UserInfo> userInfoList = sqlSessionTemplate.selectList(typeNameSpace+".findUserInfoByUserId", userId);
		return userInfoList.get(0);
	}
	
	
}
