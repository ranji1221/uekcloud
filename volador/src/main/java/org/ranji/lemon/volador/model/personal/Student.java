package org.ranji.lemon.volador.model.personal;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
@Alias("VoladorStudent")
public class Student extends AbstractModel{

	/**
	 * 学员
	 * @author 范小亚
	 * @date 2018/6/4
	 * @since JDK1.8
	 * @version 1.0
	 */
	private static final long serialVersionUID = 888849259481696698L;
	private String name;
	private String company;
	private String position;
	private String title;
	private String description;
	private String image;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }
}
