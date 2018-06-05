package org.ranji.lemon.volador.model.personal;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
@Alias("VoladorGrowthStage")
public class GrowthStage extends AbstractModel{

	/**
	 * 成长阶段
	 * @author 范小亚
	 * @date 2018/6/1
	 * @since JDK1.8
	 * @version 1.0
	 */
	private static final long serialVersionUID = -4745219974481626459L;
	private Integer number;
	private String title;
	private String description;
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}
}
