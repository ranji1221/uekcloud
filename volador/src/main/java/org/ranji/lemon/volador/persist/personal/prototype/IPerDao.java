package org.ranji.lemon.volador.persist.personal.prototype;
import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.model.personal.UserInfo;
/**
 * 用户Dao接口
 * @author 范小亚
 * @date 2018/5/8
 * @since JDK 1.8
 * @version 1.0
 *
 */
public interface IPerDao extends IGenericDao<Per, Integer>{
	/**
	 * 存储 用户-用户信息对应
	 * @param userId 用户ID
	 * @param userinfoId 用户信息ID
	 */
	public void saveUserAndUserInfoRelation(int userId, int userinfoId);
	
	/**
	 * 删除用户-用户信息对应
	 * @param userId 用户ID
	 * @param userinfoId 用户信息ID
	 */
	public void deleteUserAndUserInfoRelation(int userId, int userinfoId);
	
	/**
	 * 删除某用户的用户信息-用户信息对应
	 * @param userId 用户ID
	 */
	public void deleteUserAndUserInfoRelationByUserId(int userId);
	
	/**
	 * 根据用户ID查询全部用户-与用户信息对应
	 * @param userId 用户ID
	 * @return 用户信息ID
	 */
	public List<Integer> findUserUserInfoRelationByUserId(int userId);
	
	/**
	 * 根据用户ID查找关联用户信息
	 * @param userId  用户ID
	 * @return  关联用户信息
	 */
	public List<UserInfo> findUserInfoByUserId(int userId);
	
	/**
	 * 存储用户购买课程--与课程ID对应
	 * @param userId  用户ID
	 * @param course_id  课程ID
	 */
	public void saveUserAndBuyCourseRelation(int userId, int courseId);
	
	/**
	 * 根据用户ID查找购买的课程ID
	 * @param userId 用户ID
	 * @return 购买的课程ID列表
	 */
	public List<Integer> findBuyCourseRelationByUserId(int userId);
	
	/**
	 * 根据用户ID删除购买记录
	 * @param userId 用户ID
	 */
	public void deleteBuyCourseRelationByUserId(int userId);
	
	/**
	 * 根据用户ID和课程ID删除购买记录表
	 * @param userId 用户ID
	 * @param courseId 课程ID
	 */
	public void deleteBuyCourseRelation(int userId, int courseId);
	
	
	/**
	 * 存储用户收藏课程--与课程ID对应
	 * @param userId  用户ID
	 * @param course_id  课程ID
	 */
	public void saveUserAndCollectCourseRelation(int userId, int courseId);
	
	/**
	 * 根据用户ID查找收藏的课程ID
	 * @param userId 用户ID
	 * @return 收藏的课程ID列表
	 */
	public List<Integer> findCollectCourseRelationByUserId(int userId);
	
	/**
	 * 根据用户ID删除收藏记录
	 * @param userId 用户ID
	 */
	public void deleteCollectCourseRelationByUserId(int userId);
	
	/**
	 * 根据用户ID和课程ID删除收藏记录表
	 * @param userId 用户ID
	 * @param courseId 课程ID
	 */
	public void deleteCollectCourseRelation(int userId, int courseId);
	
	/**
	 * 存储用户正在学习课程--与课程ID对应
	 * @param userId  用户ID
	 * @param course_id  课程ID
	 */
	public void saveUserAndStudyingCourseRelation(int userId, int courseId);
	
	/**
	 * 根据用户ID查找正在学习的课程ID
	 * @param userId 用户ID
	 * @return 正在学习的课程ID列表
	 */
	public List<Integer> findStudyingCourseRelationByUserId(int userId);
	
	/**
	 * 根据用户ID删除正在学习记录
	 * @param userId 用户ID
	 */
	public void deleteStudyingCourseRelationByUserId(int userId);
	
	/**
	 * 根据用户ID和课程ID删除正在学习记录表
	 * @param userId 用户ID
	 * @param courseId 课程ID
	 */
	public void deleteStudyingCourseRelation(int userId, int courseId);
	
	/**
	 * 存储用户学习完的课程--与课程ID对应
	 * @param userId  用户ID
	 * @param course_id  课程ID
	 */
	public void saveUserAndStudyedCourseRelation(int userId, int courseId);
	
	/**
	 * 根据用户ID查找学习完的课程ID
	 * @param userId 用户ID
	 * @return 学习完的课程ID列表
	 */
	public List<Integer> findStudyedCourseRelationByUserId(int userId);
	
	/**
	 * 根据用户ID删除学习完的记录
	 * @param userId 用户ID
	 */
	public void deleteStudyedCourseRelationByUserId(int userId);
	
	/**
	 * 根据用户ID和课程ID删除学习完的记录表
	 * @param userId 用户ID
	 * @param courseId 课程ID
	 */
	public void deleteStudyedCourseRelation(int userId, int courseId);
	
	/**
	 * 存储用户作业--与作业ID对应
	 * @param userId  用户ID
	 * @param course_id  作业ID
	 */
	public void saveUserAndHomeworkRelation(int userId, int homeworkId);
	
	/**
	 * 根据用户ID查找作业ID
	 * @param userId 用户ID
	 * @return 作业ID列表
	 */
	public List<Integer> findHomeworkRelationByUserId(int userId);
	
	/**
	 * 根据用户ID删除作业的记录
	 * @param userId 用户ID
	 */
	public void deleteHomeworkRelationByUserId(int userId);
	
	/**
	 * 根据用户ID和作业ID删除记录表
	 * @param userId 用户ID
	 * @param homeworkId 作业ID
	 */
	public void deleteHomeworkRelation(int userId, int homeworkId);

}
