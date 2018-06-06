package org.ranji.lemon.volador.persist.pay.prototype;

import java.util.List;

import org.ranji.lemon.core.persist.prototype.IGenericDao;
import org.ranji.lemon.volador.model.pay.Order;

public interface IOrderDao extends IGenericDao<Order, Integer>{
    /**
     * 查询某个用户所有订单
     * @param userId
     * @return 订单列表
     */
    public List<Order> findOrderByUserId(int userId);
}
