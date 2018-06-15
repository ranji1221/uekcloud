package org.ranji.lemon.volador.persist.personal.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.persist.personal.prototype.IUserInfoDao;
import org.springframework.stereotype.Repository;
@Repository("VoladorUserInfoDaoImpl")
public class UserInfoDaoImpl extends GenericDaoImpl<UserInfo, Integer> implements IUserInfoDao{

	@Override
	public void saveUserInfo(UserInfo userinfo) {
		//Map<String, Object> params = new HashMap<String, Object>();
		//params.put("userinfo", userinfo);
		sqlSessionTemplate.insert(typeNameSpace+".save", userinfo);
	}

	@Override
	public void delteUserInfoByUserInfoId(int userinfoId) {
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("userinfoId", userinfoId);
		sqlSessionTemplate.delete(typeNameSpace+".delteUserInfoByUserInfoId", userinfoId);
		
	}

	@Override
	public List<String> findUserInfoByUserInfoId(int userinfoId) {
		return sqlSessionTemplate.selectList(typeNameSpace + ".findUserInfoByUserInfoId", userinfoId);
	}

	@Override
	public List<UserInfo> findUserInfoByEmail(String email) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findUserInfoByEmail", email);
	}

}
