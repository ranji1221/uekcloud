package org.ranji.lemon.volador.model.course;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
@Alias("VoladorCourseHomework")
public class Homework extends AbstractModel{
	/**
	 * 学生作业
	 * @author 范小亚
	 * @date 2018/5/11
	 * @since JDK1.8
	 * @version 1.0
	 */
	private static final long serialVersionUID = -6852599956884211274L;
	String address;
	String name;
	String info;
	String type;
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
        return JsonUtil.objectToJson(this);
    } 

}
