package org.ranji.lemon.volador.model.personal;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;

@Alias("VoladorStudyTime")
public class StudyTime extends AbstractModel {
	
	private static final long serialVersionUID = 1L;
	
	private double studyTime;
	
	private int  userId;

	public double getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(double studyTime) {
		this.studyTime = studyTime;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString(){
		return JsonUtil.objectToJson(this);
	}
	

}
