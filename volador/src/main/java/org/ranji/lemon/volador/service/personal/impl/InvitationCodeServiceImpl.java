package org.ranji.lemon.volador.service.personal.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.InvitationCode;
import org.ranji.lemon.volador.service.personal.prototype.IInvitationCodeService;
import org.springframework.stereotype.Service;

@Service("InvitationCodeServiceImpl")
public class InvitationCodeServiceImpl extends GenericServiceImpl<InvitationCode, Integer> implements IInvitationCodeService{

}
