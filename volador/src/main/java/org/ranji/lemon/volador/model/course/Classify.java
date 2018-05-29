package org.ranji.lemon.volador.model.course;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;


@Alias("VoladorClassify")
public class Classify extends AbstractModel {
	private String classify_name;

	public String getClassify_name() {
		return classify_name;
	}

	public void setClassify_name(String classify_name) {
		this.classify_name = classify_name;
	}
	
	@Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }

}
