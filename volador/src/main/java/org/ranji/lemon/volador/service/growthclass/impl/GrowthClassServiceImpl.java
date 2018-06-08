package org.ranji.lemon.volador.service.growthclass.impl;


import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.growthclass.GrowthClass;
import org.ranji.lemon.volador.model.growthclass.GrowthStage;
import org.ranji.lemon.volador.persist.growthclass.prototype.IGrowthClassDao;
import org.ranji.lemon.volador.service.growthclass.prototype.IGrowthClassService;
import org.springframework.stereotype.Service;
/**
 * 成长体系service实现类
 * @author 范小亚
 * @date 2018/6/1
 * @since JDK1.8
 * @version 1.0
 */
@Service("VoladorGrowthClassServiceImpl")
public class GrowthClassServiceImpl extends GenericServiceImpl<GrowthClass, Integer> implements IGrowthClassService{

	@Override
	public void saveGrowthClassAndStageRelation(int class_id, int stage_id) {
		((IGrowthClassDao) dao).saveGrowthClassAndStageRelation(class_id, stage_id);		
	}

	@Override
	public void deleteGrowthClassAndStageRelation(int class_id, int stage_id) {
		((IGrowthClassDao) dao).deleteGrowthClassAndStageRelation(class_id, stage_id);
	}

	@Override
	public void deleteGrowthClassAndStageRelationByClassId(int class_id) {
		((IGrowthClassDao) dao).deleteGrowthClassAndStageRelationByClassId(class_id);
	}

	@Override
	public void updateGrowthClassAndStageRelation(int class_id, int stage_id) {
		((IGrowthClassDao) dao).updateGrowthClassAndStageRelation(class_id, stage_id);
	}

	@Override
	public List<GrowthStage> findGrowthStageByGrowthClassId(int class_id) {
		return ((IGrowthClassDao) dao).findGrowthStageByGrowthClassId(class_id);
	}

	@Override
	public GrowthClass findGrowthClassByGrowthStageId(int stage_id) {
		return ((IGrowthClassDao) dao).findGrowthClassByGrowthStageId(stage_id).get(0);
	}

}
