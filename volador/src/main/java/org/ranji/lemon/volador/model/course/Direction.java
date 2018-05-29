package org.ranji.lemon.volador.model.course;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
@Alias("VoladorCourseDirection")
public class Direction extends AbstractModel{

	/**
	 * 课程方向
	 * @author 范小亚
	 * @date 2018/5/28
	 * @since JDK1.8
	 * @version 1.0
	 */
	private static final long serialVersionUID = 3554090777996684962L;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
