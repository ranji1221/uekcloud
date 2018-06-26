package org.ranji.lemon.volador.persist.personal.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.personal.AuthUser;
import org.ranji.lemon.volador.persist.personal.prototype.IAuthUserDao;
import org.springframework.stereotype.Repository;
/**
 * 第三方登陆接口实现类
 * @author 范小亚
 *
 */
@Repository("VoladorAuthUserDaoImpl")
public class AuthUserDaoImpl extends GenericDaoImpl<AuthUser, Integer> implements IAuthUserDao{

	@Override
	public List<AuthUser> findAuthUserByTypeAndIdentifier(String identity_type, String identifier) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("identity_type", identity_type);
		parameter.put("identifier", identifier);
		return sqlSessionTemplate.selectList(typeNameSpace+".findAuthUserByTypeAndIdentifier", parameter);
	}

}
