package org.ranji.lemon.volador.model.global;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;

@Alias("VoladorFeedback")
public class Feedback extends AbstractModel {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}

}
