package org.ranji.lemon.volador.service.global.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.global.Feedback;
import org.ranji.lemon.volador.service.global.prototype.IFeedbackService;
import org.springframework.stereotype.Service;

@Service("VoladorFeedbackServiceImpl")
public class FeedbackServiceImpl extends GenericServiceImpl<Feedback, Integer> implements IFeedbackService {

}
