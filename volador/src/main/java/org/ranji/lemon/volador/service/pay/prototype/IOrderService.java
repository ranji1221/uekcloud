package org.ranji.lemon.volador.service.pay.prototype;

import java.util.List;

import org.ranji.lemon.core.service.prototype.IGenericService;
import org.ranji.lemon.volador.model.pay.Order;

public interface IOrderService extends IGenericService<Order, Integer> {

    /**
     * 查询某个用户所有订单
     * @param userId
     * @return 订单列表
     */
    public List<Order> findOrderByUserId(int userId);
}
