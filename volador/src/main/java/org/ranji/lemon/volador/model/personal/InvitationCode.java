package org.ranji.lemon.volador.model.personal;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;

@Alias("VoladorInvitationCode")
public class InvitationCode extends AbstractModel{
	
	private static final long serialVersionUID = 1L;
	
	private String invitationCode;
	
	private int userId;

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}
	
	

}
