package org.ranji.lemon.volador.service.growthclass.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.growthclass.LabelClassify;
import org.ranji.lemon.volador.persist.growthclass.prototype.ILabelClassifyDao;
import org.ranji.lemon.volador.service.growthclass.prototype.ILabelClassifyService;
import org.springframework.stereotype.Service;
/**
 * 成长阶段标签Service实现类
 * @author 范小亚
 * @date 2018/6/8
 * @since JDK1.8
 * @version 1.0
 */
@Service("VoladorLabelClassifyServiceImpl")
public class LabelClassifyServiceImpl extends GenericServiceImpl<LabelClassify, Integer> implements ILabelClassifyService{

	@Override
	public void saveLabelAndClassifyRelation(int stagelabel_id, int labalclassify_id) {
		((ILabelClassifyDao) dao).saveLabelAndClassifyRelation(stagelabel_id, labalclassify_id);
	}

	@Override
	public Boolean deleteLabelAndClassifyRelation(Integer stagelabel_id, Integer labalclassify_id) {
		Boolean bresult = true;
		if(null != stagelabel_id && null != labalclassify_id){
			((ILabelClassifyDao) dao).deleteLabelAndClassifyRelation(stagelabel_id, labalclassify_id);
		}else if(null != stagelabel_id && null == labalclassify_id){
			((ILabelClassifyDao) dao).deletLabelAndClassifyRelationByLabelId(stagelabel_id);
		}else{
			bresult = false;
		}
		return bresult;
	}

	@Override
	public List<LabelClassify> findLabelClassifyByLabelId(Integer stagelabel_id) {
		if(null != stagelabel_id){
			return ((ILabelClassifyDao) dao).findLabelClassifyByLabelId(stagelabel_id);
		}else{
			return null;
		}
	}

}
