package org.ranji.lemon.volador.persist.personal.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.personal.AuthUser;
/**
 * 第三方登陆接口
 * @author 范小亚
 *
 */
public interface IAuthUserDao extends IGenericDao<AuthUser, Integer>{

	/**
	 * 根据第三方登陆类型和账号查找
	 * @param identity_type
	 * @param identifier
	 * @return
	 */
	public List<AuthUser> findAuthUserByTypeAndIdentifier(String identity_type, String identifier);
}
