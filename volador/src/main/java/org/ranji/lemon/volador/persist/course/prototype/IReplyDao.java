package org.ranji.lemon.volador.persist.course.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Reply;

public interface IReplyDao extends IGenericDao<Reply, Integer> {
	/**
	 * 根据评论Id查找回复列表
	 * @param commentId  评论Id
	 * @return 回复列表
	 */
	public List<Reply> findReplyByCommentId(int commentId);
	

}
