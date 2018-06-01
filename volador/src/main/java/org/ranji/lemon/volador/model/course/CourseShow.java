package org.ranji.lemon.volador.model.course;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
/**
 * 课程界面显示类
 * @author 范小亚
 *
 */
@Alias("VoladorCourseShow")
public class CourseShow extends AbstractModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -535789331412941895L;
	private String classify;
	private String courseName;
	private Integer courseId;
	private double course_price;
    private int student_count;
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public double getCourse_price() {
		return course_price;
	}
	public void setCourse_price(double course_price) {
		this.course_price = course_price;
	}
	public int getStudent_count() {
		return student_count;
	}
	public void setStudent_count(int student_count) {
		this.student_count = student_count;
	}

}
