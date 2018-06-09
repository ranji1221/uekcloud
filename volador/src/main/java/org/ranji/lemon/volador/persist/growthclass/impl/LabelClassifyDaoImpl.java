package org.ranji.lemon.volador.persist.growthclass.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.growthclass.LabelClassify;
import org.ranji.lemon.volador.persist.growthclass.prototype.ILabelClassifyDao;
import org.springframework.stereotype.Repository;

/**
 * 成长阶段标签分类Dao实现类
 * @author 范小亚
 * @date 2018/6/8
 * @since JDK1.8
 * @version 1.0
 */
@Repository("VoladorLabelClassifyDaoImpl")
public class LabelClassifyDaoImpl extends GenericDaoImpl<LabelClassify, Integer> implements ILabelClassifyDao{

	@Override
	public void saveLabelAndClassifyRelation(int stagelabel_id, int labalclassify_id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("stagelabel_id", stagelabel_id);
		param.put("labalclassify_id", labalclassify_id);
		
		sqlSessionTemplate.insert(typeNameSpace+".saveLabelAndClassifyRelation", param);
	}

	@Override
	public void deleteLabelAndClassifyRelation(int stagelabel_id, int labalclassify_id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("stagelabel_id", stagelabel_id);
		param.put("labalclassify_id", labalclassify_id);
		
		sqlSessionTemplate.delete(typeNameSpace+".deleteLabelAndClassifyRelation", param);
	}

	@Override
	public void deletLabelAndClassifyRelationByLabelId(int stagelabel_id) {
		sqlSessionTemplate.delete(typeNameSpace+".deletLabelAndClassifyRelationByLabelId", stagelabel_id);
	}

	@Override
	public List<LabelClassify> findLabelClassifyByLabelId(int stagelabel_id) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findLabelClassifyByLabelId", stagelabel_id);
	}

}
