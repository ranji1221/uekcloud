package org.ranji.lemon.volador.service.personal.prototype;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.personal.Integral;

public interface IIntegralService extends IGenericService<Integral, Integer>{
	/**
	 * 
	 * @param userId  用户ID
	 * @return        用户积分
	 */
	public Integral findIntegralByUserId(int userId);

}
