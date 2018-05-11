package org.ranji.lemon.volador.service.personal.impl;
import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.persist.auth.prototype.IRoleDao;
import org.ranji.lemon.volador.persist.personal.prototype.IUserInfoDao;
import org.ranji.lemon.volador.service.personal.prototype.IUserInfoService;
import org.springframework.stereotype.Service;
@Service("VoladorUserInfoServiceImpl")
public class UserInfoServiceImpl extends GenericServiceImpl<UserInfo, Integer> implements IUserInfoService{

	@Override
	public void saveUserInfo(UserInfo userinfo) {
		((IUserInfoDao) dao).saveUserInfo(userinfo);
		
	}

	@Override
	public void delteUserInfoByUserInfoId(int userinfoId) {
		((IUserInfoDao) dao).delteUserInfoByUserInfoId(userinfoId);
		
	}

	@Override
	public List<String> findUserInfoByUserInfoId(int userinfoId) {
		return ((IUserInfoDao) dao).findUserInfoByUserInfoId(userinfoId);
	}

}
