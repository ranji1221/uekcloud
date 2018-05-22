package org.ranji.lemon.volador.service.global.impl;

import org.ranji.lemon.core.service.impl.GenericServiceImpl;
import org.ranji.lemon.volador.model.global.Notification;
import org.ranji.lemon.volador.service.global.prototype.INotificationService;
import org.springframework.stereotype.Service;
@Service("VoladorNotificationServiceImpl")
public class NotificationServiceImpl extends GenericServiceImpl<Notification, Integer> implements INotificationService{

}
