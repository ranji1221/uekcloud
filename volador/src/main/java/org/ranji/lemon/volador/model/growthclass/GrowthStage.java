package org.ranji.lemon.volador.model.growthclass;

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
	private String image;
	
	private Integer timeCount;
	private Integer studentCount;
	private Integer coursePrice;
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
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

	public Integer getTimeCount() {
		return timeCount;
	}
	public void setTimeCount(Integer timeCount) {
		this.timeCount = timeCount;
	}
	public Integer getStudentCount() {
		return studentCount;
	}
	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	public Integer getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(Integer coursePrice) {
		this.coursePrice = coursePrice;
	}
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}
}
