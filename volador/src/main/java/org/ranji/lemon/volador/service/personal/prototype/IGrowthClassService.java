package org.ranji.lemon.volador.service.personal.prototype;

import java.util.List;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.personal.GrowthClass;
import org.ranji.lemon.volador.model.personal.GrowthStage;
/**
 * 成长体系service
 * @author 范小亚
 * @date 2018/6/1
 * @since JDK1.8
 * @version 1.0
 */
public interface IGrowthClassService extends IGenericService<GrowthClass, Integer>{
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
	public GrowthClass findGrowthClassByGrowthStageId(int stage_id);

}