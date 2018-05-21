package org.ranji.lemon.volador.service.personal.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.service.personal.prototype.ISignInService;
import org.springframework.stereotype.Service;

@Service("voladorSignInServiceImpl")
public class SignInServiceImpl extends GenericServiceImpl<SignIn, Integer> implements ISignInService {

}
