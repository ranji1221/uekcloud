package org.ranji.lemon.volador.service.growthclass.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.growthclass.StageLabel;
import org.ranji.lemon.volador.persist.growthclass.prototype.IStageLabelDao;
import org.ranji.lemon.volador.service.growthclass.prototype.ILabelClassifyService;
import org.ranji.lemon.volador.service.growthclass.prototype.IStageLabelService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private ILabelClassifyService labelClassifyService;
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

	@Override
	public void deleteStageLabelByStageLabelId(Integer growthstage_id) {
		if(null != growthstage_id){
			((IStageLabelDao) dao).deleteStageLabelByStageId(growthstage_id);
		}
	}

	@Override
	public List<Map> listStageLabelAndClassify(Integer stagelabel_id) {
		List<Map> stageLabelList = new ArrayList<Map>();
		List<StageLabel> allStageLabelList  = new ArrayList<StageLabel>();
		if(null == stagelabel_id){
			//查询全部标签及分类
			allStageLabelList = findAll();
		}else{
			//查询指定标签及分类
			allStageLabelList = findStageLabelByStageId(stagelabel_id);
		}
		if(0 != allStageLabelList.size()){
			
			for(StageLabel stageLabel:allStageLabelList){
				Map<String, Object> stageLabelMap = new HashMap<String, Object>();
				if(null != stageLabel){
					stageLabelMap.put("stageLabel", stageLabel);
					stageLabelMap.put("labelClassifyList", labelClassifyService.findLabelClassifyByLabelId(stageLabel.getId()));
				}
				stageLabelList.add(stageLabelMap);
			}
		}
		return stageLabelList;
	}

	@Override
	public void delete(Integer id) {
		if(null != id){
			((IStageLabelDao) dao).delete(id);
			//删除标签和分类的关系
			((IStageLabelDao) dao).deleteStageAndLabelRelationByLabelId(id);
			((IStageLabelDao) dao).deleteLableAndClassifyRelationByLabelId(id);
		}
	}

	@Override
	public void deleteLableAndClassifyRelationByLabelId(Integer stagelabel_id) {
		if(null != stagelabel_id){
			((IStageLabelDao) dao).deleteLableAndClassifyRelationByLabelId(stagelabel_id);
		}
	}
	
}
