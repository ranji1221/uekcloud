package org.ranji.lemon.volador.persist.course.impl;

import java.util.List;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Reply;
import org.ranji.lemon.volador.persist.course.prototype.IReplyDao;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

@Repository("VoladorReplyDaoImpl")
public class ReplyDaoImpl extends GenericDaoImpl<Reply, Integer> implements IReplyDao{

	@Override
	public List<Reply> findReplyByCommentId(int commentId) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace+".findReplyByCommentId",commentId);
	}

}
