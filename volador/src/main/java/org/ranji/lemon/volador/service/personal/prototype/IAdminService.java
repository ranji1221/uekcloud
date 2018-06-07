package org.ranji.lemon.volador.service.personal.prototype;

import java.util.List;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.personal.Admin;

public interface IAdminService extends IGenericService<Admin, Integer> {

	public boolean judgeLogin(String username,String password);
	
	public Admin findAdminByUsername(String username);
	
	public boolean parseJWT(String token);
}
