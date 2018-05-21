package org.ranji.lemon.volador.persist.personal.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.persist.personal.prototype.ISignInDao;
import org.springframework.stereotype.Repository;
@Repository("VoladorSignInDaoImpl")
public class SignInDapImpl extends  GenericDaoImpl<SignIn, Integer> implements ISignInDao{

}
