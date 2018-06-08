package org.ranji.lemon.volador.service.global.prototype;

import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.global.Feedback;

public interface IFeedbackService extends IGenericService<Feedback, Integer>{
	/**
	 * 分页返回反馈信息
	 * @return
	 */
	public Map findFeedBackByPage(int page,int limit);
}
