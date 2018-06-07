package org.ranji.lemon.volador.persist.personal.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.personal.Admin;

public interface IAdminDao extends IGenericDao<Admin, Integer>{
	public List<Admin> findAdminByUsername(String username);

}
