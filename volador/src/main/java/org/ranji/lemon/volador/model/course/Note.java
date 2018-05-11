package org.ranji.lemon.volador.model.course;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
@Alias("VoladorCourseNote")
public class Note extends AbstractModel{
	/**
	 * 学生笔记
	 * @author 范小亚
	 * @date 2018/5/11
	 * @since JDK1.8
	 * @version 1.0
	 */
	private static final long serialVersionUID = 4676610472713565395L;
	String content;
	public Note(){
		
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }
}
