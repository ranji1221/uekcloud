package org.ranji.lemon.volador.model.course;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;

/**
 * @author sertion
 * @date 2018/5/9
 * @since JDK1.8
 * @version 1.0
 */

@Alias("VoladorCourse")  //-- 为了避免引入liquid项目中也有User的别名，而引发mybatis别名设置的冲突
public class Course extends AbstractModel {
    private  String course_name;
    private String course_info;
    private double course_price;
    private int student_count;

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_info() {
        return course_info;
    }

    public void setCourse_info(String course_info) {
        this.course_info = course_info;
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

    @Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }
}
