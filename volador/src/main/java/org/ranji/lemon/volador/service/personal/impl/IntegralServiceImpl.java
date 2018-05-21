package org.ranji.lemon.volador.service.personal.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.Integral;
import org.ranji.lemon.volador.service.personal.prototype.IIntegralService;
import org.springframework.stereotype.Service;

@Service("VoladorIntegralServiceImpl")
public class IntegralServiceImpl extends GenericServiceImpl<Integral, Integer> implements IIntegralService {

}
