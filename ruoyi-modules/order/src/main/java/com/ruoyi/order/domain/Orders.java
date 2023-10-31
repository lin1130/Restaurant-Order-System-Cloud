package com.ruoyi.order.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * order对象 orders
 * 
 * @author group14
 * @date 2023-10-31
 */
public class Orders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** item id */
    private Long orderId;

    /** user id */
    @Excel(name = "user id")
    private Long userId;

    /** order time */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "order time", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderTime;

    /** order */
    @Excel(name = "order")
    private String orderState;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setOrderTime(Date orderTime) 
    {
        this.orderTime = orderTime;
    }

    public Date getOrderTime() 
    {
        return orderTime;
    }
    public void setOrderState(String orderState) 
    {
        this.orderState = orderState;
    }

    public String getOrderState() 
    {
        return orderState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("orderTime", getOrderTime())
            .append("orderState", getOrderState())
            .toString();
    }
}
