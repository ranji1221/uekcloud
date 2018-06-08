package org.ranji.lemon.volador.persist.global.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.global.Feedback;
import org.ranji.lemon.volador.persist.global.prototype.IFeedbackDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorFeedbackDapImpl")
public class FeedbackDaoImpl extends GenericDaoImpl<Feedback, Integer> implements IFeedbackDao{

	/**
	 * 分页查询反馈信息
	 * @param page
	 * @param limit
	 * @return
	 */
	@Override
	public List<Feedback> findFeedbackByPage(int page, int limit) {
		// TODO Auto-generated method stub
		Map parameterMap = new HashMap<>();
		parameterMap.put("startNumber", (page-1)*limit);
		parameterMap.put("offSet", limit);
	
		return sqlSessionTemplate.selectList(typeNameSpace+".findFeedbackByPage", parameterMap);
	}

	/**
	 * 查询反馈的总条数
	 * @return
	 */
	@Override
	public int feedbackCount() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne(typeNameSpace+".feedbackCount");
	}
	
}
