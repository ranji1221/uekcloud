package org.ranji.lemon.volador.persist.course.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.course.Comment;
import org.ranji.lemon.volador.persist.course.prototype.ICommentDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorCommentDaoImpl")
public class CommentDaoImpl extends GenericDaoImpl<Comment,Integer> implements ICommentDao {
	
	@Override
	public void savaCommentAndUserRelation(int commentid,int userid){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userId", userid);
		map.put("commentId", commentid);
		sqlSessionTemplate.insert(typeNameSpace+".savaCommentAndUserRelation", map);
	}
	
	
	@Override
	public void saveCommentAndChapterRelation(int commentid,int chapterid){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("chapterId", chapterid);
		map.put("commentId", commentid);
		sqlSessionTemplate.insert(typeNameSpace+".saveCommentAndChapterRelation", map);
	}


	@Override
	public void deleteCommentAndUserRelation(int commentid, int userid) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userid", userid);
		map.put("commentId", commentid);
		sqlSessionTemplate.delete(typeNameSpace+".deleteCommentAndUserRelation", map);
		
	}


	@Override
	public void deleteCommentAndChapterRelation(int commentid, int chapterid) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("commentid", commentid);
		map.put("chapterid", chapterid);
		sqlSessionTemplate.delete(typeNameSpace+".deleteCommentAndChapterRelation", map);
		
	}


	@Override
	public List<Integer> findUserIdByCommentId(int commentId) {
		// TODO Auto-generated method stub
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("commentId", commentId);
		return sqlSessionTemplate.selectList(typeNameSpace+".findUserIdByCommentId", map);
	}
	

}
