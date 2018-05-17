package org.ranji.lemon.volador.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.ranji.lemon.volador.model.auth.User;
import org.ranji.lemon.volador.model.personal.Per;
import org.ranji.lemon.volador.service.auth.prototype.IUserService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class SystemRealm extends AuthorizingRealm {
	
	@Autowired
	@Lazy		//-- 解决redis缓存和shiro冲突的问题
	//飞鱼平台个人中心登录实现，注释原来用户认证
	//private IUserService userService;
	
	private IPerService personalService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {
		AuthenticationInfo authenInfo = null;
		UsernamePasswordToken token = (UsernamePasswordToken)authToken;
		//飞鱼平台个人中心登录实现，注释原来用户认证
		//User user = userService.findByUserName(token.getUsername());
		Per user = personalService.findByUserName(token.getUsername());
		if(user!=null)authenInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),getName());
		return authenInfo;
	}
}
