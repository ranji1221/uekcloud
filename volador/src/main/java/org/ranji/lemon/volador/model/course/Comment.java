package org.ranji.lemon.volador.model.course;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;

@Alias("VoladorComment")
public class Comment extends AbstractModel {
	
	private String content;
	private String nickName;
	private String head_image;
	private int userId;
	private int good;
	private int reprot;


	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getReprot() {
		return reprot;
	}

	public void setReprot(int reprot) {
		this.reprot = reprot;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHead_image() {
		return head_image;
	}

	public void setHead_image(String head_image) {
		this.head_image = head_image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return JsonUtil.objectToJson(this);
	}
}
