package org.ranji.lemon.volador.persist.personal.impl;

import java.util.List;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.personal.Admin;
import org.ranji.lemon.volador.persist.personal.prototype.IAdminDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorAdminDaoImpl")
public class AdminDaoImpl extends GenericDaoImpl<Admin, Integer> implements IAdminDao{

	@Override
	public List<Admin> findAdminByUsername(String username) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList(typeNameSpace+".findAdminByUsername",username);
	}

}
