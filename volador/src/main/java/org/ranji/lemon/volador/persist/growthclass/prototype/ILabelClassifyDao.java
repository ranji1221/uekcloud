package org.ranji.lemon.volador.persist.growthclass.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.growthclass.LabelClassify;
/**
 * 成长阶段标签分类Dao
 * @author 范小亚
 * @date 2018/6/8
 * @since JDK1.8
 * @version 1.0
 */
public interface ILabelClassifyDao extends IGenericDao<LabelClassify, Integer>{
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
	public void deleteLabelAndClassifyRelation(int stagelabel_id, int labalclassify_id);
	/**
	 * 根据阶段标签ID删除阶段标签与标签分类关系
	 * @param stagelabel_id
	 */
	public void deletLabelAndClassifyRelationByLabelId(int stagelabel_id);
	/**
	 * 根据阶段标签ID查询阶段分类
	 * @param stagelabel_id
	 * @return
	 */
	public List<LabelClassify> findLabelClassifyByLabelId(int stagelabel_id);

}
