package org.ranji.lemon.volador.persist.personal.prototype;
import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.personal.UserInfo;
/**
 * 用户信息DAO接口
 * @author Administrator
 * @author 范小亚
 * @date 2018/5/8
 * @since JDK 1.8
 * @version 10
 */
public interface IUserInfoDao extends IGenericDao<UserInfo, Integer>{
	/**
	 * 保存用户信息
	 * @param userinfo
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

}
