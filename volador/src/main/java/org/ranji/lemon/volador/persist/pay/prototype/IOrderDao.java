package org.ranji.lemon.volador.persist.pay.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.pay.Order;
import org.ranji.lemon.volador.model.pay.VoladorCode;

public interface IOrderDao extends IGenericDao<Order, Integer>{
    /**
     * 查询某个用户所有订单
     * @param userId
     * @return 订单列表
     */
    public List<Order> findOrderByUserId(int userId);
    
    /**
     * 分页查询所有订单
     * @param userId
     * @return 订单列表
     */
    public List<Order> findPageOrderList(int page,int limit);
    
    /**
     * 订单总数
     * @param userId
     * @return 订单列表
     */
    public int orderCount();
    
    /**
     * 生成激活码
     * @param courseId
     * @param voladorCode
     */
    public void saveVoladorCode(int courseId,String voladorCode);
    
    /**
     * 使用激活码
     * @param courseId
     * @param voladorCode
     */
    public void useVoladorCode(String voladorCode);
    
    /**
     * 查询激活码
     * @param courseId
     * @param voladorCode
     */
    public VoladorCode findVoladorCode(String voladorCode);
    
    /**
     * 查询所有激活码
     * @param courseId
     * @param voladorCode
     */
    public List<VoladorCode> findVoladorCodeAll();
    
}
