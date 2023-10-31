package com.ruoyi.details.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * details对象 order_details
 * 
 * @author group14
 * @date 2023-10-31
 */
public class OrderDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** detail id */
    private Long detailId;

    /** order id */
    @Excel(name = "order id")
    private Long orderId;

    /** item id */
    @Excel(name = "item id")
    private Long itemId;

    /** quantity */
    @Excel(name = "quantity")
    private Long quantity;

    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
    }
    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setItemId(Long itemId) 
    {
        this.itemId = itemId;
    }

    public Long getItemId() 
    {
        return itemId;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("detailId", getDetailId())
            .append("orderId", getOrderId())
            .append("itemId", getItemId())
            .append("quantity", getQuantity())
            .toString();
    }
}
