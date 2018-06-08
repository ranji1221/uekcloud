package org.ranji.lemon.volador.persist.global.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.global.Feedback;
import org.ranji.lemon.volador.persist.global.prototype.IFeedbackDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorFeedbackDapImpl")
public class FeedbackDaoImpl extends GenericDaoImpl<Feedback, Integer> implements IFeedbackDao{

}
