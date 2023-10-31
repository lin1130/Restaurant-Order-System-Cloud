package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.Orders;

/**
 * orderMapper接口
 * 
 * @author group14
 * @date 2023-10-31
 */
public interface OrdersMapper 
{
    /**
     * 查询order
     * 
     * @param orderId order主键
     * @return order
     */
    public Orders selectOrdersByOrderId(Long orderId);

    /**
     * 查询order列表
     * 
     * @param orders order
     * @return order集合
     */
    public List<Orders> selectOrdersList(Orders orders);

    /**
     * 新增order
     * 
     * @param orders order
     * @return 结果
     */
    public int insertOrders(Orders orders);

    /**
     * 修改order
     * 
     * @param orders order
     * @return 结果
     */
    public int updateOrders(Orders orders);

    /**
     * 删除order
     * 
     * @param orderId order主键
     * @return 结果
     */
    public int deleteOrdersByOrderId(Long orderId);

    /**
     * 批量删除order
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrdersByOrderIds(Long[] orderIds);
}
