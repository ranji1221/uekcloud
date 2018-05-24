package org.ranji.lemon.volador.service.personal.prototype;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.personal.SignIn;

public interface ISignInService extends IGenericService<SignIn, Integer>{
	/**
	 * 
	 * @param userId  用户ID
	 * @return        用户签到
	 */
	public SignIn findSignInByUserId(int userId);

}
