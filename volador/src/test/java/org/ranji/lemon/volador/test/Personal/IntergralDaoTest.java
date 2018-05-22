package org.ranji.lemon.volador.test.Personal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.global.Announcement;
import org.ranji.lemon.volador.model.global.Notification;
import org.ranji.lemon.volador.model.personal.Integral;
import org.ranji.lemon.volador.model.personal.InvitationCode;
import org.ranji.lemon.volador.model.personal.SignIn;
import org.ranji.lemon.volador.model.personal.StudyTime;
import org.ranji.lemon.volador.service.global.prototype.IAnnouncementService;
import org.ranji.lemon.volador.service.global.prototype.INotificationService;
import org.ranji.lemon.volador.service.personal.prototype.IIntegralService;
import org.ranji.lemon.volador.service.personal.prototype.IInvitationCodeService;
import org.ranji.lemon.volador.service.personal.prototype.ISignInService;
import org.ranji.lemon.volador.service.personal.prototype.IStudyTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class IntergralDaoTest {
	
	@Autowired
	private IIntegralService integralService;
	
	@Autowired
	private ISignInService signInService;
	
	@Autowired
	private IStudyTimeService studyTimeService;
	
	@Autowired
	private IInvitationCodeService invitationCodeService;
	
	@Autowired
	private INotificationService notificationService;
	
	@Autowired
	private IAnnouncementService announcementService;
	
	/*@Test
	public void addUser(){
		Integral integral = new Integral();
		integral.setUserId(2);
		integral.setIntegralNumber(150);
		integralService.save(integral);
		
	}
	
	@Test
	public void addSignIn(){
		
		SignIn signin = new SignIn();
		signin.setUserId(2);
		signin.setDay(300);
		signInService.save(signin);
	}

	
	@Test
	public void addInvitationCode(){
		InvitationCode invitationcode = new InvitationCode(); 
		invitationcode.setInvitationCode("dsfdsgg");
		invitationcode.setUserId(2);
		invitationCodeService.save(invitationcode);
		
	}
	
	@Test
	public void addStudyTime(){
		
		StudyTime studytime = new StudyTime();
		studytime.setStudyTime(3.5);
		studytime.setUserId(2);
		studyTimeService.save(studytime);
		
		
	}*/
	
	@Test
	public void addNotification(){
		Notification notification = new Notification();
		notification.setNotificationTitle("系统维护");
		notification.setNotificationContent("今晚18点系统将要进行维护");
		notificationService.save(notification);
	}
	
	@Test
	public void addAnnouncement(){
		Announcement announcement = new Announcement();
		announcement.setAnnouncementTitle("公告");
		announcement.setAnnouncementContent("系统将于今天开始暂停更新");
	    announcementService.save(announcement);
	}
}
