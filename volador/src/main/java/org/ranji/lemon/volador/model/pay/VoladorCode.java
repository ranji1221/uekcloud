package org.ranji.lemon.volador.model.pay;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.util.JsonUtil;

@Alias("voladorCode")
public class VoladorCode {
	private int courseId;
	private String voladorCode;
	private int status;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getVoladorCode() {
		return voladorCode;
	}
	public void setVoladorCode(String voladorCode) {
		this.voladorCode = voladorCode;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	@Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }

}
