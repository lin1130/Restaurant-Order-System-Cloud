package com.ruoyi.details.mapper;

import java.util.List;
import com.ruoyi.details.domain.OrderDetails;

/**
 * detailsMapper接口
 * 
 * @author group14
 * @date 2023-10-31
 */
public interface OrderDetailsMapper 
{
    /**
     * 查询details
     * 
     * @param detailId details主键
     * @return details
     */
    public OrderDetails selectOrderDetailsByDetailId(Long detailId);

    /**
     * 查询details列表
     * 
     * @param orderDetails details
     * @return details集合
     */
    public List<OrderDetails> selectOrderDetailsList(OrderDetails orderDetails);

    /**
     * 新增details
     * 
     * @param orderDetails details
     * @return 结果
     */
    public int insertOrderDetails(OrderDetails orderDetails);

    /**
     * 修改details
     * 
     * @param orderDetails details
     * @return 结果
     */
    public int updateOrderDetails(OrderDetails orderDetails);

    /**
     * 删除details
     * 
     * @param detailId details主键
     * @return 结果
     */
    public int deleteOrderDetailsByDetailId(Long detailId);

    /**
     * 批量删除details
     * 
     * @param detailIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOrderDetailsByDetailIds(Long[] detailIds);
}
