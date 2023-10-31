package com.ruoyi.order.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.OrdersMapper;
import com.ruoyi.order.domain.Orders;
import com.ruoyi.order.service.IOrdersService;

/**
 * orderService业务层处理
 * 
 * @author group14
 * @date 2023-10-31
 */
@Service
public class OrdersServiceImpl implements IOrdersService 
{
    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 查询order
     * 
     * @param orderId order主键
     * @return order
     */
    @Override
    public Orders selectOrdersByOrderId(Long orderId)
    {
        return ordersMapper.selectOrdersByOrderId(orderId);
    }

    /**
     * 查询order列表
     * 
     * @param orders order
     * @return order
     */
    @Override
    public List<Orders> selectOrdersList(Orders orders)
    {
        return ordersMapper.selectOrdersList(orders);
    }

    /**
     * 新增order
     * 
     * @param orders order
     * @return 结果
     */
    @Override
    public int insertOrders(Orders orders)
    {
        return ordersMapper.insertOrders(orders);
    }

    /**
     * 修改order
     * 
     * @param orders order
     * @return 结果
     */
    @Override
    public int updateOrders(Orders orders)
    {
        return ordersMapper.updateOrders(orders);
    }

    /**
     * 批量删除order
     * 
     * @param orderIds 需要删除的order主键
     * @return 结果
     */
    @Override
    public int deleteOrdersByOrderIds(Long[] orderIds)
    {
        return ordersMapper.deleteOrdersByOrderIds(orderIds);
    }

    /**
     * 删除order信息
     * 
     * @param orderId order主键
     * @return 结果
     */
    @Override
    public int deleteOrdersByOrderId(Long orderId)
    {
        return ordersMapper.deleteOrdersByOrderId(orderId);
    }
}
