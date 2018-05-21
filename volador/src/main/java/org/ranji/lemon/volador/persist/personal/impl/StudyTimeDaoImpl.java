package org.ranji.lemon.volador.persist.personal.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.personal.StudyTime;
import org.ranji.lemon.volador.persist.personal.prototype.IStudyTimeDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorStudyTimeDaoImpl")
public class StudyTimeDaoImpl extends GenericDaoImpl<StudyTime, Integer> implements IStudyTimeDao {

}
