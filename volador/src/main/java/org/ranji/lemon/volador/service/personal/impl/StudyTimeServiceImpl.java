package org.ranji.lemon.volador.service.personal.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.personal.StudyTime;
import org.ranji.lemon.volador.service.personal.prototype.IStudyTimeService;
import org.springframework.stereotype.Service;

@Service("VoladorStudyTimeServiceImpl")
public class StudyTimeServiceImpl extends GenericServiceImpl<StudyTime, Integer> implements IStudyTimeService{

}
