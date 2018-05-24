package org.ranji.lemon.volador.service.personal.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.Integral;
import org.ranji.lemon.volador.persist.personal.prototype.IIntegralDao;
import org.ranji.lemon.volador.service.personal.prototype.IIntegralService;
import org.springframework.stereotype.Service;

@Service("VoladorIntegralServiceImpl")
public class IntegralServiceImpl extends GenericServiceImpl<Integral, Integer> implements IIntegralService {
	
	@Override
	public Integral findIntegralByUserId(int userId) {
		List<Integral> integralList = ((IIntegralDao) dao).findIntegralByUserId(userId);
		return integralList.get(0);
	}	
}
