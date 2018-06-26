package org.ranji.lemon.volador.service.personal.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.AuthUser;
import org.ranji.lemon.volador.persist.personal.prototype.IAuthUserDao;
import org.ranji.lemon.volador.service.personal.prototype.IAuthUserService;
import org.springframework.stereotype.Service;
/**
 * 第三方登陆service实现类
 * @author 范小亚
 *
 */
@Service("VoladorAuthUserServiceImpl")
public class AuthUserServiceImpl extends GenericServiceImpl<AuthUser, Integer> implements IAuthUserService{

	@Override
	public Boolean isFirstLogin(String identity_type, String identifier) {
		Boolean result;
		//用户是否登陆
		List<AuthUser> authUserList = ((IAuthUserDao) dao).findAuthUserByTypeAndIdentifier(identity_type, identifier);
		if(0 == authUserList.size()){
			//用户第一次登陆
			result = true;
		}else{
			//用户已经登陆
			result = false;
		}
		return result;
	}

	@Override
	public AuthUser findAuthUser(String identity_type, String identifier) {
		List<AuthUser> authUserList = ((IAuthUserDao) dao).findAuthUserByTypeAndIdentifier(identity_type, identifier);
		if(0 != authUserList.size()){
			return authUserList.get(0);
		}else{
			return null;
		}
	}
}
