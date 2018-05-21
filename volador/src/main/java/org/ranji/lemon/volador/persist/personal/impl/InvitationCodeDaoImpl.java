package org.ranji.lemon.volador.persist.personal.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.personal.InvitationCode;
import org.ranji.lemon.volador.persist.personal.prototype.IInvitationCodeDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorInvitarionCodeDaoImpl")
public class InvitationCodeDaoImpl extends GenericDaoImpl<InvitationCode, Integer> implements IInvitationCodeDao{
	

}
