package org.ranji.lemon.volador.service.course.prototype;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.course.Comment;

public interface ICommentService extends IGenericService<Comment, Integer>{
	
	/**
	 * 
	 * @param commentid 评论id
	 * @param userid    用户id
	 */
	public void savaCommentAndUserRelation(int commentid,int userid);
	
	/**
	 * 
	 * @param commentid 评论id
	 * @param chapterid 章节id
	 */
	public void saveCommentAndChapterRelation(int commentid,int chapterid);
	
	
	/**
	 * 删除用户和评论的关系 
	 * @param commentid 评论id
	 * @param userid    用户id
	 */
	public void  deleteCommentAndUserRelation(int commentid,int userid);
	
	/**
	 * 删除章节评论的关系
	 * @param commentid 评论id
	 * @param chapterid 章节id
	 */
	public void  deleteCommentAndChapterRelation(int commentid,int chapterid);
	
}
