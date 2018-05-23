package org.ranji.lemon.volador.model.course;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;

/**
 * @author sertion
 * @date 2018/5/22
 * @since JDK1.8
 * @version 1.0
 */

@Alias("VoladorChapterTitle")
public class ChapterTitle extends AbstractModel{
	private String chapter_title;
	private int course_id;
	
	
	public String getChapter_title() {
		return chapter_title;
	}
	public void setChapter_title(String chapter_title) {
		this.chapter_title = chapter_title;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getChapter_title_order() {
		return chapter_title_order;
	}
	public void setChapter_title_order(int chapter_title_order) {
		this.chapter_title_order = chapter_title_order;
	}
	private int chapter_title_order;

}
