package org.ranji.lemon.volador.service.personal.impl;

import java.util.List;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.persist.personal.prototype.ISignInDao;
import org.ranji.lemon.volador.service.personal.prototype.ISignInService;
import org.springframework.stereotype.Service;

@Service("voladorSignInServiceImpl")
public class SignInServiceImpl extends GenericServiceImpl<SignIn, Integer> implements ISignInService {

	@Override
	public SignIn findSignInByUserId(int userId) {
		List<SignIn> signInList = ((ISignInDao) dao).findSignInByUserId(userId);
		if(0 != signInList.size()){
			return signInList.get(0);
		}else{
			return null;
		}
		
	}

}
