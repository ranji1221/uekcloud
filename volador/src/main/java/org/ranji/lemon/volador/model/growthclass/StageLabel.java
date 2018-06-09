package org.ranji.lemon.volador.model.growthclass;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
@Alias("VoladorGrowthClassStageLabel")
public class StageLabel extends AbstractModel{

	/**
	 * 成长标签
	 * @author 范小亚
	 * @date 2018/6/8
	 * @since JDK1.8
	 * @version 1.0
	 */
	private static final long serialVersionUID = -8572570710249795061L;
	private String label;
	private String title;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}
}
