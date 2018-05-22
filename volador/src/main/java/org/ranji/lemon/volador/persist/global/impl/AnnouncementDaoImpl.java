package org.ranji.lemon.volador.persist.global.impl;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.global.Announcement;
import org.ranji.lemon.volador.persist.global.prototype.IAnnouncementDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorAnnouncementDaoImppl")
public class AnnouncementDaoImpl extends GenericDaoImpl<Announcement, Integer> implements IAnnouncementDao {

}
