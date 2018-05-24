package org.ranji.lemon.volador.persist.course.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Comment;

public interface ICommentDao extends IGenericDao<Comment,Integer>{
	/**
	 * 增加用户评论关系
	 * @param commentid 评论id
	 * @param userid    用户id
	 */
	public void savaCommentAndUserRelation(int commentid,int userid);
	
	/**
	 * 增加章节评论关系
	 * @param commentid 评论id
	 * @param chapterid 章节id
	 */
	public void saveCommentAndChapterRelation(int commentid,int chapterid);
	
	/**
	 * 删除用户评论关系
	 * @param commentid 评论id
	 * @param userid    用户id
	 */
	public void deleteCommentAndUserRelation(int commentid,int userid);
	
	/**
	 * 删除章节评论关系
	 * @param commentid 评论id
	 * @param chapterid 章节id
	 */
	public void deleteCommentAndChapterRelation(int commentid,int chapterid);
	
	/**
	 * 根据评论id查询用户id
	 * @param commentid 评论id
	 * @return 用户id
	 */
	public List<Integer> findUserIdByCommentId(int commentId);
	
	/**
	 * 删除课程与评论关系
	 * @param courseId 课程id
	 * @param commentId 章节id
	 */
	public void deleteCourseAndCommentRelation(int commentId,int courseId);
	
	/**
	 * 增加章节评论关系
	 * @param commentid 评论id
	 * @param courseId 课程id
	 */
	public void saveCourseAndCommentRelation(int commentId,int courseId);
	
	

}
