package org.ranji.lemon.volador.service.growthclass.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.growthclass.StageLabel;
import org.ranji.lemon.volador.persist.growthclass.prototype.IStageLabelDao;
import org.ranji.lemon.volador.service.growthclass.prototype.IStageLabelService;
import org.springframework.stereotype.Service;
/**
 * 成长阶段标签Service实现
 * @author 范小亚
 * @date 2018/6/8
 * @since JDK1.8
 * @version 1.0
 */
@Service("VoladorStageLabelServiceImpl")
public class StageLabelServiceImpl extends GenericServiceImpl<StageLabel, Integer> implements IStageLabelService{

	@Override
	public void saveStageAndLabelRelation(int growthstage_id, int stagelabel_id) {
		((IStageLabelDao) dao).saveStageAndLabelRelation(growthstage_id, stagelabel_id);		
	}

	@Override
	public Boolean deleteStageAndLabelRelation(Integer growthstage_id, Integer stagelabel_id) {
		Boolean bresult = true;
		if(null != growthstage_id && null != stagelabel_id){
			((IStageLabelDao) dao).deleteStageAndLabelRelation(growthstage_id, stagelabel_id);
		}else if(null != growthstage_id && null == stagelabel_id){
			((IStageLabelDao) dao).deleteStageAndLabelRelationByStageId(growthstage_id);
		}	
		return bresult;
	}

	@Override
	public List<StageLabel> findStageLabelByStageId(Integer growthstage_id) {
		if(null != growthstage_id){
			return ((IStageLabelDao) dao).findStageLabelByStageId(growthstage_id);
		}else{
			return null;
		}
	}
}
