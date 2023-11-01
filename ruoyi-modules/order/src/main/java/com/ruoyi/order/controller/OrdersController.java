package com.ruoyi.order.controller;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.order.domain.Orders;
import com.ruoyi.order.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * orderController
 *
 * @author group14
 * @date 2023-10-31
 */
@RestController
@RequestMapping("/orders")
public class OrdersController extends BaseController
{
    @Autowired
    private IOrdersService ordersService;

    /**
     * 查询order列表
     */
    @RequiresPermissions("order:orders:list")
    @GetMapping("/list")
    public TableDataInfo list(Orders orders)
    {
        startPage();
        List<Orders> list = ordersService.selectOrdersList(orders);
        return getDataTable(list);
    }

    /**
     * 导出order列表
     */
    @RequiresPermissions("order:orders:export")
    @Log(title = "order", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Orders orders)
    {
        List<Orders> list = ordersService.selectOrdersList(orders);
        ExcelUtil<Orders> util = new ExcelUtil<Orders>(Orders.class);
        util.exportExcel(response, list, "order数据");
    }

    /**
     * 获取order详细信息
     */
    @RequiresPermissions("order:orders:query")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(ordersService.selectOrdersByOrderId(orderId));
    }

    /**
     * 新增order
     */
    @RequiresPermissions("order:orders:add")
    @Log(title = "order", businessType = BusinessType.INSERT)
    @PostMapping
//    public AjaxResult add(@RequestBody Orders orders)
//    {
//        return toAjax(ordersService.insertOrders(orders));
//    }
    public Orders add(@RequestBody Orders orders) {
        return ordersService.insertOrders(orders);
    }

    /**
     * 修改order
     */
    @RequiresPermissions("order:orders:edit")
    @Log(title = "order", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Orders orders)
    {
        return toAjax(ordersService.updateOrders(orders));
    }

    /**
     * 删除order
     */
    @RequiresPermissions("order:orders:remove")
    @Log(title = "order", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(ordersService.deleteOrdersByOrderIds(orderIds));
    }
}
