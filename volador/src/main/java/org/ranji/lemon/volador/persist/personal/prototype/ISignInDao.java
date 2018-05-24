package org.ranji.lemon.volador.persist.personal.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.personal.SignIn;

public interface ISignInDao extends IGenericDao<SignIn, Integer> {
	/**
	 * 
	 * @param userId  用户ID
	 * @return        用户签到
	 */
	public List<SignIn> findSignInByUserId(int userId);

}
