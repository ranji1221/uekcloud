package org.ranji.lemon.volador.service.pay.prototype;

import java.util.List;
import java.util.Map;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.pay.Order;
import org.ranji.lemon.volador.model.pay.VoladorCode;

public interface IOrderService extends IGenericService<Order, Integer> {

    /**
     * 查询某个用户所有订单
     * @param userId
     * @return 订单列表
     */
    public List<Order> findOrderByUserId(int userId);
    
    public Map pageOrder(int page,int limit);
    
    
    /**
     * 生成激活码
     * @param courseId
     * @param voladorCode
     */
    public void saveVoladorCode(int courseId);
    
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
