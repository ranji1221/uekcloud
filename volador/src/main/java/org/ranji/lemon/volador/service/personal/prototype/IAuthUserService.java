package org.ranji.lemon.volador.service.personal.prototype;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.personal.AuthUser;
/**
 * 第三方登陆service
 * @author 范小亚
 *
 */
public interface IAuthUserService extends IGenericService<AuthUser, Integer>{

	/**
	 * 用户是否第一次登陆
	 * @param identity_type
	 * @param identifier
	 * @return
	 */
	public Boolean isFirstLogin(String identity_type, String identifier);
	/**
	 * 获取第三方登陆账号
	 * @param identity_type
	 * @param identifier
	 * @return
	 */
	public AuthUser findAuthUser(String identity_type, String identifier);
}
