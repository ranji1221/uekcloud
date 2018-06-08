package org.ranji.lemon.volador.model.pay;

import java.util.Date;

import org.apache.ibatis.type.Alias;
import org.ranji.lemon.core.model.AbstractModel;
import org.ranji.lemon.core.util.JsonUtil;

@Alias("voladorPayOrder")
public class Order extends AbstractModel {

	//实付金额
	private double payMent;		
	/*
	 * 状态：
	 * 		1 	未付款
	 * 		2  	建议成功
	 * 		3	交易关闭
	 * */
	private int status;	
	//付款时间
	private Date payMentTime;
	//用户ID
	private int userId;
	//交易完成时间
	private Date endTime;
	//交易关闭时间
	private Date closeTime;
	//购买课程Id
	private int courseId;
	//订单号
	private String orderNumber;
	
	
	public double getPayMent() {
		return payMent;
	}
	public void setPayMent(double payMent) {
		this.payMent = payMent;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getPayMentTime() {
		return payMentTime;
	}
	public void setPayMentTime(Date payMentTime) {
		this.payMentTime = payMentTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
    public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	@Override
    public String toString() {
        return JsonUtil.objectToJson(this);
    }
}
