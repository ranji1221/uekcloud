package org.ranji.lemon.volador.model.global;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;

@Alias("VoladorAnnouncement")
public class Announcement extends AbstractModel {
	private static final long serialVersionUID = 1L;

	private String announcementTitle;
	
	private String announcementContent;

	public String getAnnouncementTitle() {
		return announcementTitle;
	}

	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}

	public String getAnnouncementContent() {
		return announcementContent;
	}

	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
