package org.ranji.lemon.volador.model.course;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;
@Alias("VoladorCourseThemeShow")
public class CourseThemeShow extends AbstractModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 435759736910511772L;
	private String theme;
	private String description;
	private List<CourseShow> courseShow;
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<CourseShow> getCourseShow() {
		return courseShow;
	}
	public void setCourseShow(List<CourseShow> courseShow) {
		this.courseShow = courseShow;
	}

	@Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }
}
