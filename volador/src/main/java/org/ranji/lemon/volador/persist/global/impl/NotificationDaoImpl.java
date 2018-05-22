package org.ranji.lemon.volador.persist.global.impl;

import javax.xml.ws.RespectBinding;

import org.ranji.lemon.core.persist.impl.GenericDaoImpl;
import org.ranji.lemon.volador.model.global.Notification;
import org.ranji.lemon.volador.persist.global.prototype.INotificationDao;
import org.springframework.stereotype.Repository;

@Repository("VoladorNotificationDaoImpl")
public class NotificationDaoImpl extends GenericDaoImpl<Notification, Integer> implements INotificationDao{

}
