package org.ranji.lemon.volador.service.course.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Reply;
import org.ranji.lemon.volador.persist.course.prototype.IReplyDao;
import org.ranji.lemon.volador.service.course.prototype.IReplyService;
import org.springframework.stereotype.Service;

@Service("VoladorReplyServiceImpl")
public class ReplyServiceImpl extends GenericServiceImpl<Reply, Integer> implements IReplyService{

	@Override
	public List<Reply> findReplyByCommentId(int commentId) {
		// TODO Auto-generated method stub
		return ((IReplyDao) dao).findReplyByCommentId(commentId);
	}

}
