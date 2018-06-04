package org.ranji.lemon.volador.model.course;

import java.util.Date;

import org.apache.ibatis.type.Alias;

@Alias("VoladorStudyingCourse")
public class StudyingCourse {
	private Date updateTime;
	private String courseName;
	private int chapterId;
	private String chapterName;
	private int noteCount;
	private int commentCount;
	private String courseImage;
	private String updateChapter;
	private int courseId;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getUpdateChapter() {
		return updateChapter;
	}
	public void setUpdateChapter(String updateChapter) {
		this.updateChapter = updateChapter;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getChapterId() {
		return chapterId;
	}
	public void setChapterId(int chapterId) {
		this.chapterId = chapterId;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public int getNoteCount() {
		return noteCount;
	}
	public void setNoteCount(int noteCount) {
		this.noteCount = noteCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getCourseImage() {
		return courseImage;
	}
	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
	}

}
