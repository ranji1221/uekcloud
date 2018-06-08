package org.ranji.lemon.volador.util;

import java.util.Date;

/**
 * @author sertion
 * @date 2018/6/8
 * @since JDK1.8
 * @version 1.0
 */
public class OrderNumberUtil {
	
	/**
	 * 创建订单号
	 * @param userId 用户Id
	 * @param courseId 课程Id
	 * @return 订单号
	 * */
	public static String createOrderNumber(int userId,int courseId){
		Date date=new Date();
		return "UEK"+date.getTime()+userId+courseId;
	} 

}
