package org.ranji.lemon.volador.persist.growthclass.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.growthclass.StageLabel;
/**
 * 成长阶段标签Dao
 * @author 范小亚
 * @date 2018/6/8
 * @since JDK1.8
 * @version 1.0
 */
public interface IStageLabelDao extends IGenericDao<StageLabel, Integer>{
	/**
	 * 保存阶段及阶段标签关系
	 * @param growthstage_id
	 * @param stagelabel_id
	 */
	public void saveStageAndLabelRelation(int growthstage_id, int stagelabel_id);
	/**
	 * 删除阶段及阶段标签关系
	 * @param growthstage_id
	 * @param stagelabel_id
	 */
	public void deleteStageAndLabelRelation(int growthstage_id, int stagelabel_id);
	/**
	 * 根据阶段ID删除阶段及阶段标签关系
	 * @param growthstage_id
	 */
	public void deleteStageAndLabelRelationByStageId(int growthstage_id);
	/**
	 * 根据阶段ID查询阶段图标
	 * @param growthstage_id
	 * @return
	 */
	public List<StageLabel> findStageLabelByStageId(int growthstage_id);	
}
