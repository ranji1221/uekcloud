package org.ranji.lemon.volador.persist.growthclass.prototype;


import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.growthclass.GrowthClass;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
/**
 * 成长体系Dao
 * @author 范小亚
 * @date 2018/6/1
 * @since JDK1.8
 * @version 1.0
 */
public interface IGrowthClassDao extends IGenericDao<GrowthClass, Integer>{
	/**
	 * 保存成长方向和阶段的关系
	 * @param class_id          成长方向ID
	 * @param stage_id			成长阶段ID
	 */
	public void saveGrowthClassAndStageRelation(int class_id, int stage_id);
	
	/**
	 * 删除成长方向和阶段的关系
	 * @param class_id          成长方向ID
	 * @param stage_id			成长阶段ID
	 */
	public void deleteGrowthClassAndStageRelation(int class_id, int stage_id);
	
	/**
	 * 通过成长方向 删除成长方向和阶段的关系
	 * @param class_id          成长方向ID
	 */
	public void deleteGrowthClassAndStageRelationByClassId(int class_id);
	
	/**
	 * 更新成长方向和阶段的关系
	 * @param class_id          成长方向ID
	 * @param stage_id			成长阶段ID
	 */
	public void updateGrowthClassAndStageRelation(int class_id, int stage_id);
	
	/**
	 * 通过成长方向查询成长阶段
	 * @param class_id          成长方向ID
	 */
	public List<GrowthStage> findGrowthStageByGrowthClassId(int class_id);
	
	/**
	 * 通过成长阶段查询成长方向
	 * @param stage_id          成长阶段ID
	 */
	public List<GrowthClass> findGrowthClassByGrowthStageId(int stage_id);
	
	/**
	 * 保存用户收藏的职业导航
	 * @param user_id
	 * @param growthclass_id
	 */
	public void saveUserAndGrowthClassRelation(int user_id, int growthclass_id, int chapter_id);
	
	/**
	 * 保存用户收藏的职业导航
	 * @param user_id
	 * @param growthclass_id
	 */
	public void updateUserAndGrowthClassRelation(int user_id, int growthclass_id, int chapter_id);
	
	/**
	 * 通过用户ID查找用户收藏的所有职业导航
	 * @param user_id
	 * @return
	 */
	public List<GrowthClass> findGrowthClassByUserId(int user_id);
	
	/**
	 * 根据用户ID和职业导航ID获取章节
	 * @param user_id
	 * @param growthclass_id
	 * @return 章节列表
	 */
	public List<Chapter> findChapterByUserIdAndGrowthClassId(int user_id, int growthclass_id);
	
	/**
	 * 根据课程ID查找职业导航
	 * @param course_id
	 * @return
	 */
	public List<GrowthClass> findGrowthClassByCourseId(int course_id);

}
