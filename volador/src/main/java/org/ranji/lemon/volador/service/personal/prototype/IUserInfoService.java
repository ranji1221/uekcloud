package org.ranji.lemon.volador.service.personal.prototype;
import java.util.List;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.personal.UserInfo;
public interface IUserInfoService extends IGenericService<UserInfo, Integer>{
	/**
	 * 保存用户信息
	 * @param userinfoId
	 */
	public void saveUserInfo(UserInfo userinfo);
	
	/**
	 * 根据用户信息ID删除用户信息
	 * @param userinfoId
	 */
	public void delteUserInfoByUserInfoId(int userinfoId);
	
	/**
	 * 根据用户信息ID查询用户信息
	 * @param userinfoId
	 * @return
	 */
	public List<String> findUserInfoByUserInfoId(int userinfoId);
	
	/**
	 * 通过email查找用户信息
	 * @param email
	 * @return
	 */
	public UserInfo findUserInfoByEmail(String email);

}
