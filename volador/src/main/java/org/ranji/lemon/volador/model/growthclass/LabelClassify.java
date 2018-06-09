package org.ranji.lemon.volador.model.growthclass;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
@Alias("VoladorLabelClassify")
public class LabelClassify extends AbstractModel{

	/**
	 * 成长标签分类
	 * @author 范小亚
	 * @date 2018/6/8
	 * @since JDK1.8
	 * @version 1.0
	 */
	private static final long serialVersionUID = -2200881813914710622L;
	private String classify;
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}

}
