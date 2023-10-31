package com.ruoyi.details.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.details.mapper.OrderDetailsMapper;
import com.ruoyi.details.domain.OrderDetails;
import com.ruoyi.details.service.IOrderDetailsService;

/**
 * detailsService业务层处理
 * 
 * @author group14
 * @date 2023-10-31
 */
@Service
public class OrderDetailsServiceImpl implements IOrderDetailsService 
{
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    /**
     * 查询details
     * 
     * @param detailId details主键
     * @return details
     */
    @Override
    public OrderDetails selectOrderDetailsByDetailId(Long detailId)
    {
        return orderDetailsMapper.selectOrderDetailsByDetailId(detailId);
    }

    /**
     * 查询details列表
     * 
     * @param orderDetails details
     * @return details
     */
    @Override
    public List<OrderDetails> selectOrderDetailsList(OrderDetails orderDetails)
    {
        return orderDetailsMapper.selectOrderDetailsList(orderDetails);
    }

    /**
     * 新增details
     * 
     * @param orderDetails details
     * @return 结果
     */
    @Override
    public int insertOrderDetails(OrderDetails orderDetails)
    {
        return orderDetailsMapper.insertOrderDetails(orderDetails);
    }

    /**
     * 修改details
     * 
     * @param orderDetails details
     * @return 结果
     */
    @Override
    public int updateOrderDetails(OrderDetails orderDetails)
    {
        return orderDetailsMapper.updateOrderDetails(orderDetails);
    }

    /**
     * 批量删除details
     * 
     * @param detailIds 需要删除的details主键
     * @return 结果
     */
    @Override
    public int deleteOrderDetailsByDetailIds(Long[] detailIds)
    {
        return orderDetailsMapper.deleteOrderDetailsByDetailIds(detailIds);
    }

    /**
     * 删除details信息
     * 
     * @param detailId details主键
     * @return 结果
     */
    @Override
    public int deleteOrderDetailsByDetailId(Long detailId)
    {
        return orderDetailsMapper.deleteOrderDetailsByDetailId(detailId);
    }
}
