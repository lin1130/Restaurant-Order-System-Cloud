package com.ruoyi.item.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.item.domain.Item;
import com.ruoyi.item.service.IItemService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * itemController
 * 
 * @author group14
 * @date 2023-10-31
 */
@RestController
@RequestMapping("/item")
public class ItemController extends BaseController
{
    @Autowired
    private IItemService itemService;

    /**
     * 查询item列表
     */
    @RequiresPermissions("item:item:list")
    @GetMapping("/list")
    public TableDataInfo list(Item item)
    {
        startPage();
        List<Item> list = itemService.selectItemList(item);
        return getDataTable(list);
    }

    /**
     * 导出item列表
     */
    @RequiresPermissions("item:item:export")
    @Log(title = "item", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Item item)
    {
        List<Item> list = itemService.selectItemList(item);
        ExcelUtil<Item> util = new ExcelUtil<Item>(Item.class);
        util.exportExcel(response, list, "item数据");
    }

    /**
     * 获取item详细信息
     */
    @RequiresPermissions("item:item:query")
    @GetMapping(value = "/{itemId}")
    public AjaxResult getInfo(@PathVariable("itemId") Long itemId)
    {
        return success(itemService.selectItemByItemId(itemId));
    }

    /**
     * 新增item
     */
    @RequiresPermissions("item:item:add")
    @Log(title = "item", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Item item)
    {
        return toAjax(itemService.insertItem(item));
    }

    /**
     * 修改item
     */
    @RequiresPermissions("item:item:edit")
    @Log(title = "item", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Item item)
    {
        return toAjax(itemService.updateItem(item));
    }

    /**
     * 删除item
     */
    @RequiresPermissions("item:item:remove")
    @Log(title = "item", businessType = BusinessType.DELETE)
	@DeleteMapping("/{itemIds}")
    public AjaxResult remove(@PathVariable Long[] itemIds)
    {
        return toAjax(itemService.deleteItemByItemIds(itemIds));
    }
}
