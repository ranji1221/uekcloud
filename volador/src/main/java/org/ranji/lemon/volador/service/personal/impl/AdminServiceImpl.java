package org.ranji.lemon.volador.service.personal.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.Admin;
import org.ranji.lemon.volador.persist.personal.prototype.IAdminDao;
import org.ranji.lemon.volador.service.personal.prototype.IAdminService;
import org.ranji.lemon.volador.util.JWTUtil;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

@Service("VoladorAdminServiceImpl")
public class AdminServiceImpl extends GenericServiceImpl<Admin, Integer> implements IAdminService{

	@Override
	public boolean judgeLogin(String username, String password) {
		// TODO Auto-generated method stub
		boolean result=false;
		List<Admin> admin=((IAdminDao) dao).findAdminByUsername(username);
		try {
			if(admin.get(0).getPassword().equals(password)){
				result=true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	

	@Override
	public Admin findAdminByUsername(String username) {
		// TODO Auto-generated method stub
		return ((IAdminDao) dao).findAdminByUsername(username).get(0);
	}



	@Override
	public boolean parseJWT(String token) {
		// TODO Auto-generated method stub
		try {
			Claims claims=JWTUtil.parseJWT(token);
			try {
				List<Admin> admin=((IAdminDao) dao).findAdminByUsername(claims.getId());
				admin.get(0);
				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}	
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
	}

}
