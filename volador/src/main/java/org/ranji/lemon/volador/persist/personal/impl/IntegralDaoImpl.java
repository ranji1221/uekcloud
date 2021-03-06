package org.ranji.lemon.volador.persist.personal.impl;

import java.util.List;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.personal.Integral;
import org.ranji.lemon.volador.persist.personal.prototype.IIntegralDao;
import org.springframework.stereotype.Repository;
@Repository("VoladorIntegralDaoImpl")
public class IntegralDaoImpl extends GenericDaoImpl<Integral, Integer> implements IIntegralDao {

	@Override
	public List<Integral> findIntegralByUserId(int userId) {
		return sqlSessionTemplate.selectList(typeNameSpace + ".findIntegralByUserId", userId);
	}

}
