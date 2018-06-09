package org.ranji.lemon.volador.persist.growthclass.prototype;


import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
/**
 * 成长阶段Dao
 * @author 范小亚
 * @date 2018/6/1
 * @since JDK1.8
 * @version 1.0
 */
public interface IGrowthStageDao extends IGenericDao<GrowthStage, Integer>{
	/**
	 * 保存成长阶段与课程章节的关系
	 * @param stage_id				成长阶段ID
	 * @param course_id				课程ID	
	 */
	public void saveGrowthStageAndCourseRelation(int stage_id, int course_id);
	
	/**
	 * 删除成长阶段与课程章节的关系
	 * @param stage_id				成长阶段ID
	 * @param course_id				课程ID
	 */
	public void deleteGrowthStageAndCourseRelation(int stage_id, int course_id);

	/**
	 * 通过阶段ID删除成长阶段与课程章节的关系
	 * @param stage_id				成长阶段ID
	 */
	public void deleteGrowthStageAndCourseRelationByStageId(int stage_id);
	
	/**
	 * 更新成长阶段与课程章节的关系
	 * @param stage_id				成长阶段ID
	 * @param course_id				课程ID
	 */
	public void updateGrowthStageAndCourseRelation(int stage_id, int course_id);
	
	/**
	 * 通过阶段ID查找课程列表
	 * @param course_id				成长阶段ID
	 * @return						课程列表
	 */
	public List<Course> findCourseByGrowthStageId(int stage_id);
	
	/**
	 * 保存用户正在学习的阶段及学习的章节
	 * @param user_id
	 * @param growthclass_id
	 * @param growthstage_id
	 * @param chapter_id
	 */
	public void saveUserStudyStage(int user_id, int growthclass_id, int growthstage_id, int chapter_id);
	
	/**
	 * 根据用户Id，职业导航ID，章节ID获取用户学习的阶段
	 * @param user_id
	 * @param growthclass_id
	 * @param chapter_id
	 * @return
	 */
	public List<GrowthStage> findStudyStageByUserIdAndChapterId(int user_id,  int growthclass_id, int chapter_id);
	
	/**
	 * 更新用户正在学习的阶段及学习的章节
	 * @param user_id
	 * @param growthclass_id
	 * @param growthstage_id
	 * @param chapter_id
	 */
	public void updateUserStudyStage(int user_id, int growthclass_id, int growthstage_id, int chapter_id);
	
	/**
	 * 通过用户ID，职业导航ID，阶段ID查找正在学习的章节
	 * @param user_id
	 * @param growthclass_id
	 * @param growthstage_id
	 * @return
	 */
	public List<Chapter> findChapterByUserIdAndClassIdAndStageId(int user_id, int growthclass_id, int growthstage_id);
}
