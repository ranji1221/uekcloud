package org.ranji.lemon.volador.model.course;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;

/**
 * 个人中心用户模块
 * @author 范小亚
 * @date 2018/5/18
 * @since JDK1.8
 * @version 1.0
 */

@Alias("VoladorCourseTheme")
public class Theme extends AbstractModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7279857846326823016L;
	private String title;
	private String description;
	
	public Theme(){
		
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribe() {
		return description;
	}

	public void setDescribe(String description) {
		this.description = description;
	}
	
	@Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }

}
