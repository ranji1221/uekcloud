package org.ranji.lemon.volador.service.course.prototype;

import java.util.List;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.course.Reply;

public interface IReplyService extends IGenericService<Reply, Integer> {
	/**
	 * 根据评论Id查找回复列表
	 * @param commentId  评论Id
	 * @return 回复列表
	 */
	public List<Reply> findReplyByCommentId(int commentId);

}
