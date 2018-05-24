package org.ranji.lemon.volador.persist.personal.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.personal.Integral;

public interface IIntegralDao extends IGenericDao<Integral, Integer> {
	
	/**
	 * 
	 * @param userId  用户ID
	 * @return        用户积分
	 */
	public List<Integral> findIntegralByUserId(int userId);

}
