package org.ranji.lemon.volador.model.personal;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
@Alias("VoladorGrowthClass")
public class GrowthClass extends AbstractModel{

	/**
	 * 职业导航（成长路径）
	 * @author 范小亚
	 * @date 2018/6/1
	 * @since JDK1.8
	 * @version 1.0
	 */
	private static final long serialVersionUID = 2636402586349769096L;
	private String image = "images/wzq_work_img.jpg";
	private String title;
	private String send_word;
	private String description;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	
	public String getSend_word() {
		return send_word;
	}
	public void setSend_word(String send_word) {
		this.send_word = send_word;
	}
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}

}
