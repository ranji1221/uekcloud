package org.ranji.lemon.volador.service.course.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.course.Comment;
import org.ranji.lemon.volador.persist.course.prototype.ICommentDao;
import org.ranji.lemon.volador.service.course.prototype.ICommentService;
import org.springframework.stereotype.Service;

@Service("VoladorCommentServiceImpl")
public class CommentServiceImpl extends GenericServiceImpl<Comment, Integer> implements ICommentService{
	
	@Override
	public void save(Comment entity) {
		
		super.save(entity);
		
    }
	
	@Override
	public void savaCommentAndUserRelation(int commentid,int userid){
		
		((ICommentDao) dao).savaCommentAndUserRelation(commentid, userid);
	}
	
	@Override
	public void saveCommentAndChapterRelation(int commentid,int chapterid){
		((ICommentDao)dao).saveCommentAndChapterRelation(commentid, chapterid);
	}

	@Override
	public void deleteCommentAndUserRelation(int commentid, int userid) {
		// TODO Auto-generated method stub
		((ICommentDao)dao).deleteCommentAndChapterRelation(commentid, userid);
	}

	@Override
	public void deleteCommentAndChapterRelation(int commentid, int chapterid) {
		// TODO Auto-generated method stub
		((ICommentDao)dao).deleteCommentAndChapterRelation(commentid, chapterid);
		
	}


}
