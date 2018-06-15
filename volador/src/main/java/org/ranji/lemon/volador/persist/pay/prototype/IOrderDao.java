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
     * @param voladorCode
     */
    public void useVoladorCode(String voladorCode);
    
    /**
     * 查询激活码
     * @param voladorCode
     */
    public VoladorCode findVoladorCode(String voladorCode);
    
    /**
     * 查询所有激活码
     */
    public List<VoladorCode> findVoladorCodeAll();
    
    /**
     * 查询激活码总数
     */
    public int findVoladorCodeCount();
    
    /**
     * 分页查询所有激活码
     * @param page
     * @param limit
     */
    public List<VoladorCode> findPageVoladorCodeAll(int page,int limit);
    
    /**
     * 分页条件查询所有激活码
     * @param page
     * @param limit
     */
    public List<VoladorCode> findPageVoladorCode(int page,int limit,int status);
    
    
    /**
     * 条件查询激活码总数
     */
    public int findPageVoladorCodeCount(int status);
    
}
