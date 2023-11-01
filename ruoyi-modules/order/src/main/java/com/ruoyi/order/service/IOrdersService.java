package com.ruoyi.order.service;

import java.util.List;
import com.ruoyi.order.domain.Orders;

/**
 * orderService接口
 *
 * @author group14
 * @date 2023-10-31
 */
public interface IOrdersService
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
    public Orders insertOrders(Orders orders);

    /**
     * 修改order
     *
     * @param orders order
     * @return 结果
     */
    public int updateOrders(Orders orders);

    /**
     * 批量删除order
     *
     * @param orderIds 需要删除的order主键集合
     * @return 结果
     */
    public int deleteOrdersByOrderIds(Long[] orderIds);

    /**
     * 删除order信息
     *
     * @param orderId order主键
     * @return 结果
     */
    public int deleteOrdersByOrderId(Long orderId);
}
