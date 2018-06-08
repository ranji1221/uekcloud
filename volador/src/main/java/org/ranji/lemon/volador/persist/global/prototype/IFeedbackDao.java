package org.ranji.lemon.volador.persist.global.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.global.Feedback;

public interface IFeedbackDao extends IGenericDao<Feedback, Integer>{
	/**
	 * 分页查询反馈信息
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Feedback> findFeedbackByPage(int page,int limit);
	
	/**
	 * 查询反馈的总条数
	 * @return
	 */
	public int feedbackCount(); 
	   
}
