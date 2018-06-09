package org.ranji.lemon.volador.service.growthclass.prototype;

import java.util.List;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.growthclass.LabelClassify;
/**
 * 成长阶段标签分类Service
 * @author 范小亚
 * @date 2018/6/8
 * @since JDK1.8
 * @version 1.0
 */
public interface ILabelClassifyService extends IGenericService<LabelClassify, Integer>{
	/**
	 * 保存阶段标签与标签分类关系
	 * @param stagelabel_id
	 * @param labalclassify_id
	 */
	public void saveLabelAndClassifyRelation(int stagelabel_id, int labalclassify_id);
	/**
	 * 删除阶段标签与标签分类关系
	 * @param stagelabel_id
	 * @param labalclassify_id
	 */
	public Boolean deleteLabelAndClassifyRelation(Integer stagelabel_id, Integer labalclassify_id);
	
	/**
	 * 根据阶段标签ID查询阶段分类
	 * @param stagelabel_id
	 * @return
	 */
	public List<LabelClassify> findLabelClassifyByLabelId(Integer stagelabel_id);

}
