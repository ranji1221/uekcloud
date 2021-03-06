package org.ranji.lemon.volador.service.personal.prototype;
import java.util.Date;
import java.util.List;

import org.ranji.lemon.core.pagination.PagerModel;
import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.StudyingCourse;
import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.model.personal.UserInfo;
/**
 * personal模块中IUserService接口
 * @author 范小亚
 *@version 1.0
 *@since JDK1.8
 *@date 2018/5/8
 */
public interface IPerService extends IGenericService<Per, Integer>{
	/**
	 * 根据用户名查找用户
	 * @param userName
	 * @return 用户
	 */
	public Per findByUserName(String userName);
	
    /**
     * 存储用户 - 用户信息对应
     * @param userId 用户id
     * @param userinfoId 用户信息id
     */
	public void saveUserAndUserInfoRelation(int userId, int userinfoId);
	
	/**
	 * 删除用户-与用户信息对应
	 * @param userId 用户id
	 * @param userinfoId 用户信息id
	 */
	public void deleteUserAndUserInfoRelation(int userId, int userinfoId);
	
	/**
	 * 删除某用户的全部信息-与用户信息对应
	 * @param userId 用户id
	 */
	public void deleteUserUserInfoByUserId(int userId);
	
	/**
	 * 根据用户ID查找用户信息ID集合
	 * @param userId 用户ID
	 * @return 用户信息ID集合
	 */
	public List<Integer> findUserUserInfoRelationByUserId(int userId);
	
	/**
	 * 根据用户ID查找用户信息
	 * @param userId  用户ID
	 * @return  用户信息
	 */
	public UserInfo findUserInfoByUserId(int userId);
	
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
	public void saveUserAndStudyingCourseRelation(int userId, int courseId,Date updateTime,int chapterId);
	
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
	
	/**
	 * 根据笔记ID查找对应用户
	 * @param noteId  笔记ID
	 * @return        用户信息
	 */
	public UserInfo findUserInfoByNoteId(int noteId);
	
	
	/**
	 * 根据用户ID分页查找正在学习课程
	 * @param userId	  用户id	 
	 * @param page  	  页码
	 * @param page 		 偏移量
	 * @return        课程列表pageModel
	 */
	public PagerModel<Course> findPageStudyingCourseByUser(int userId,int page,int limit);
	
	/**
	 * 根据用户ID分页查找已学习课程
	 * @param userId	  用户id	 
	 * @param page  	  页码
	 * @param page 		 偏移量
	 * @return        课程列表
	 */
	public PagerModel<Course> findPageFinishCourseByUser(int userId,int page,int limit);
	
	/**
	 * 根据用户ID分页查找收藏课程
	 * @param userId	  用户id	 
	 * @param page  	  页码
	 * @param page 		 偏移量
	 * @return        课程列表
	 */
	public PagerModel<Course> findPageCollectCourseByUser(int userId,int page,int limit);
	
	/**
	 * 查询正在学习课程其他信息
	 * @param userId	  用户id	 
	 * @return        总数列表
	 */
	public StudyingCourse findStudyingCourse(int userId,int courseId);

}
