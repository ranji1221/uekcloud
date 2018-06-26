package org.ranji.lemon.volador.test.course;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.ranji.lemon.core.util.JsonUtil;
import org.ranji.lemon.volador.VoladorApplication;
import org.ranji.lemon.volador.model.course.Chapter;
import org.ranji.lemon.volador.model.course.Comment;
import org.ranji.lemon.volador.model.course.Course;
import org.ranji.lemon.volador.model.course.Reply;
import org.ranji.lemon.volador.model.course.Teacher;
import org.ranji.lemon.volador.model.pay.Order;
import org.ranji.lemon.volador.model.pay.VoladorCode;
import org.ranji.lemon.volador.model.personal.UserInfo;
import org.ranji.lemon.volador.persist.course.prototype.ICommentDao;
import org.ranji.lemon.volador.persist.pay.prototype.IOrderDao;
import org.ranji.lemon.volador.service.auth.prototype.IUserService;
import org.ranji.lemon.volador.service.course.prototype.IChapterService;
import org.ranji.lemon.volador.service.course.prototype.IClassifyService;
import org.ranji.lemon.volador.service.course.prototype.ICommentService;
import org.ranji.lemon.volador.service.course.prototype.ICourseService;
import org.ranji.lemon.volador.service.course.prototype.IReplyService;
import org.ranji.lemon.volador.service.pay.prototype.IOrderService;
import org.ranji.lemon.volador.service.personal.prototype.IPerService;
import org.ranji.lemon.volador.service.personal.prototype.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=VoladorApplication.class)  //-- 指定Spring-Boot的启动类
public class CourseService {

	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IChapterService chapterService;
	
	@Autowired
	private IClassifyService classifyService;
	
	@Autowired
	private IPerService personalService;
	
	@Autowired
	private ICommentService commentService;
	
	@Autowired
	private IReplyService replyService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private IOrderDao orderDao;
	
	@Autowired
	private ICommentDao commentDao;
	
	
	@Test
	public void testReplyService(){
		
		Comment comment=commentDao.find(19);
//		Course course=new Course();
//		course.setCourse_name("测试");
//		course.setCourse_info("测试");
//		course.setCourse_price(13);
//		course.setFlag("hot");
//		courseService.save(course);
		
//		Comment comment=new Comment();
//		comment.setId(24);
////		comment.setGood(23);
//		comment.setReprot(1);
//		commentService.update(comment);
		
//		orderService.saveVoladorCode(36);
//		int a=orderDao.findPageVoladorCodeCount(1);
//		System.out.println(a);
//		List<VoladorCode> voladorCodeList=orderDao.findPageVoladorCode(1, 2,-1);
//		System.out.println(voladorCodeList);
		
//		List<Course> courseList=courseService.keywordSreachCourse("python");
//		System.out.println(JsonUtil.objectToJson(courseList));
//		Order order=new Order();
//		order.setGuid("a4a82bed5222499db36af107c5527491");
//		order.setCloseTime(new Date());
//		order.setStatus(3);
//		
//		List<Order> orderList=orderDao.findOrderByUserId(34);
//		
//		List<Order> orderList1=orderService.findOrderByUserId(34);
//		
//		Order order1=orderService.find(1);
//		System.out.println(order1.toString());
//		System.out.println(orderList.toString());
//		System.out.println(orderList1.toString());
		
		
		
		
//		Reply reply=new Reply();
//		reply.setReply("测试回复3");
//		reply.setUserId(32);
//		reply.setReplyUserId(23);
////		replyService.save(reply);
//		int commentId=1;
//		List<Reply> replyList=replyService.findReplyByCommentId(commentId);
//		System.out.println(replyList.toString());
	}
	
//	private ICourseService courseService;
//	
//	@Test
//	public void testSaveCourseAndTeacherRelation(){
//		courseService.saveCourseAndTeacherRelation(4, 12);
//	}
//	
//	@Test
//	public void testDelateCourseAndTeacherRelation(){
//		courseService.deleteCourseAndTeacherRelation(1, 2);
//	}
//	
//	@Test
//	public void testFindTeacherbyCourse(){
//		
//		Course course=new Course();
//		course.setCourse_name("大数据阿萨德阿萨德");
//		course.setCourse_price(222);
//		course.setCourse_info("asdasd");
//		courseService.save(course);
//		List<Teacher> listTeacher;
//		listTeacher=courseService.findTeacherbyCourse(3);
//		courseService.deleteCourseAndChapterRelation(1, 1);
//		courseService.saveCourseAndChapterRelation(2, 1);
//		int userId=32;
//		List<Integer> listCourseId= personalService.findStudyingCourseRelationByUserId(userId);
//		List<Course> courseList = new ArrayList<Course>() ;
//		for(int i=0;i<listCourseId.size();i++){
//			Course course=courseService.find(listCourseId.get(i));
//			courseList.add(course);
//			System.out.println(courseList.get(0).getCourse_name());
//		}
//		int chapterId=1;
//		//查询章节信息
//		Chapter chapter=chapterService.find(chapterId);
//		
//		//根据章节id获取评论列表
//		List<Comment> commentList = chapterService.findCommentListByChapter(chapterId);
		
//		Chapter chapter=chapterService.find(chapterId);
//		
//		//根据章节id获取评论列表
//		List<Comment> commentList = chapterService.findCommentListByChapter(chapterId);
		
//		int commentId=commentList.get(1).getId();
//		int user_Id=commentService.findUserIdByCommentId(commentId).get(0);
//		int as=commentService.findUserIdByCommentId(3).get(0);
//		for(int i=0;i<commentList.size();i++){
//			int commentId=commentList.get(i).getId();
//			int user_Id=commentService.findUserIdByCommentId(commentId).get(0);
//			UserInfo userInfo=personalService.findUserInfoByUserId(user_Id);
//			commentList.get(i).setNickName(userInfo.getNickname());
//			commentList.get(i).setHead_image(userInfo.getHead_image());
//		}
		
//		List<Integer> userinfo_idList=personalService.findUserUserInfoRelationByUserId(userId);
//		List<UserInfo> userInfoList =new ArrayList<UserInfo>();
//		userInfoList.add(userInfoService.find(userinfo_idList.get(0)));
//		List<Chapter> chapterlist=courseService.findChapterbyCourse(2);
//		System.out.println(chapterlist.get(0).getChapter_name());
//		System.out.println(listTeacher.get(0).getTeacher_name());
		
		
//	}

}
