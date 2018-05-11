package org.ranji.lemon.volador.model.personal;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
@Alias("VoladorPerUser")
public class Per extends AbstractModel{

	/**
	 * 个人中心用户模块
	 * @author 范小亚
	 * @date 2018/5/8
	 * @since JDK1.8
	 * @version 1.0
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	public Per(){
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}

}
