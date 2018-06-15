package org.ranji.lemon.volador.service.global.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.global.Feedback;
import org.ranji.lemon.volador.persist.global.prototype.IFeedbackDao;
import org.ranji.lemon.volador.service.global.prototype.IFeedbackService;
import org.springframework.stereotype.Service;

@Service("VoladorFeedbackServiceImpl")
public class FeedbackServiceImpl extends GenericServiceImpl<Feedback, Integer> implements IFeedbackService {

	/**
	 * 分页返回反馈信息
	 * @return
	 */
	@Override
	public Map findFeedBackByPage(int page, int limit) {
		// TODO Auto-generated method stub
		Map resultMap = new HashMap<>();
		int totalCount = ((IFeedbackDao)dao).feedbackCount();
		int pageCount = 0;
		if(totalCount%limit==0){
			pageCount = totalCount/limit;
		}else{
			pageCount = totalCount/limit + 1;
		}
		Map feebackListMap = new HashMap<>();
		List<Feedback> feedbackList = ((IFeedbackDao)dao).findFeedbackByPage(page, limit);
		feebackListMap.put("feedbackList", feedbackList);
		resultMap.put("totalCount", totalCount);
		resultMap.put("pageCount", pageCount);
		resultMap.put("pageNo", page);
		resultMap.put("data", feebackListMap);
		return resultMap;
	}

}
