package org.ranji.lemon.volador.model.global;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;

@Alias("VoladorNotification")
public class Notification extends AbstractModel {

	private static final long serialVersionUID = 1L;

	private String notificationTitle;
	
	private String notificationContent;

	public String getNotificationTitle() {
		return notificationTitle;
	}

	public void setNotificationTitle(String notificationTitle) {
		this.notificationTitle = notificationTitle;
	}

	public String getNotificationContent() {
		return notificationContent;
	}

	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
