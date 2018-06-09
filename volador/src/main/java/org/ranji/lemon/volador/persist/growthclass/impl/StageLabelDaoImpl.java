package org.ranji.lemon.volador.persist.growthclass.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.growthclass.StageLabel;
import org.ranji.lemon.volador.persist.growthclass.prototype.IStageLabelDao;
import org.springframework.stereotype.Repository;
/**
 * 成长阶段标签Dao实现
 * @author 范小亚
 * @date 2018/6/8
 * @since JDK1.8
 * @version 1.0
 */
@Repository("VoladorStageLabelDaoImpl")
public class StageLabelDaoImpl extends GenericDaoImpl<StageLabel, Integer> implements IStageLabelDao{

	@Override
	public void saveStageAndLabelRelation(int growthstage_id, int stagelabel_id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("growthstage_id", growthstage_id);
		parameter.put("stagelabel_id", stagelabel_id);
		sqlSessionTemplate.insert(typeNameSpace+".saveStageAndLabelRelation", parameter);
	}

	@Override
	public void deleteStageAndLabelRelation(int growthstage_id, int stagelabel_id) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("growthstage_id", growthstage_id);
		parameter.put("stagelabel_id", stagelabel_id);
		sqlSessionTemplate.delete(typeNameSpace+".deleteStageAndLabelRelation", parameter);
	}

	@Override
	public void deleteStageAndLabelRelationByStageId(int growthstage_id) {
		sqlSessionTemplate.delete(typeNameSpace+".deleteStageAndLabelRelationByStageId", growthstage_id);
	}

	@Override
	public List<StageLabel> findStageLabelByStageId(int growthstage_id) {
		return sqlSessionTemplate.selectList(typeNameSpace+".findStageLabelByStageId", growthstage_id);
	}
}
