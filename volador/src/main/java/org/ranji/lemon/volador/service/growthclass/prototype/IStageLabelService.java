package org.ranji.lemon.volador.service.growthclass.prototype;

import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.growthclass.StageLabel;
/**
 * 成长阶段标签Service
 * @author 范小亚
 * @date 2018/6/8
 * @since JDK1.8
 * @version 1.0
 */
public interface IStageLabelService extends IGenericService<StageLabel, Integer>{
	
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
	 * @return
	 */
	public Boolean deleteStageAndLabelRelation(Integer growthstage_id, Integer stagelabel_id);
	
	/**
	 * 根据阶段ID查询阶段图标
	 * @param growthstage_id
	 * @return
	 */
	public List<StageLabel> findStageLabelByStageId(Integer growthstage_id);

	/**
	 * 根据阶段ID删除阶段图标
	 * @param growthstage_id
	 */
	public void deleteStageLabelByStageLabelId(Integer stagelabel_id);
	
	/**
	 * 根据标签ID查询标签及分类
	 * @param stagelabel_id
	 * @return
	 */
	public List<Map> listStageLabelAndClassify(Integer stagelabel_id);
	
	/**
	 * 通过标签ID删除标签与分类的关系
	 * @param stagelabel_id
	 */
	public void deleteLableAndClassifyRelationByLabelId(Integer stagelabel_id);
}
