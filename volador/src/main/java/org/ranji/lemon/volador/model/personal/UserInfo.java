package org.ranji.lemon.volador.model.personal;
import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
@Alias("VoladorUserInfo")
public class UserInfo extends AbstractModel{

	/**
	 * 个人中心用户信息模块
	 * @author 范小亚
	 * @date 2018/5/8
	 * @since JDK1.8
	 * @version 1.0
	 */
	private static final long serialVersionUID = -2975570328825147993L;
	private String gender;
	private String email;
	private String nickname;
	private String head_image;
	private String real_name;
	private String idcard;
	private String address;
	private String wechat;
	private String qq;
	
	public UserInfo(){
		
	}

	public String getGender() {
		return gender;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHead_image() {
		return head_image;
	}

	public void setHead_image(String head_image) {
		this.head_image = head_image;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getQQ() {
		return qq;
	}

	public void setQQ(String qQ) {
		qq = qQ;
	}

	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}
	
		
	}
