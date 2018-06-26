package org.ranji.lemon.volador.model.personal;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
/**
 * 第三方登陆类
 * @author 范小亚
 *
 */
@Alias("VoladorAuthUser")
public class AuthUser extends AbstractModel{
	/**
	 * 第三方登陆类
	 */
	private static final long serialVersionUID = -4201774225019043074L;
	//第三方登陆类型，qq，微信，微博
	private String identity_type;
	//第三方登陆账号
	private String identifier;
	//第三方登陆密钥
	private String credential;
	//第三方账号信息表id
	private Integer userinfo_id;
	public String getIdentity_type() {
		return identity_type;
	}
	public void setIdentity_type(String identity_type) {
		this.identity_type = identity_type;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public Integer getUserinfo_id() {
		return userinfo_id;
	}
	public void setUserinfo_id(Integer userinfo_id) {
		this.userinfo_id = userinfo_id;
	}
	
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}

}
