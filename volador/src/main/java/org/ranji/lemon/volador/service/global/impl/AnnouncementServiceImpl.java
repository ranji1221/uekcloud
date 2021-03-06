package org.ranji.lemon.volador.service.global.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.global.Announcement;
import org.ranji.lemon.volador.service.global.prototype.IAnnouncementService;
import org.springframework.stereotype.Service;

@Service("VoladorAnnounceServiceImpl")
public class AnnouncementServiceImpl extends GenericServiceImpl<Announcement, Integer> implements IAnnouncementService{

}
