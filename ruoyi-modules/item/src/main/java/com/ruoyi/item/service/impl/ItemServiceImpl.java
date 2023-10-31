package com.ruoyi.item.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.item.mapper.ItemMapper;
import com.ruoyi.item.domain.Item;
import com.ruoyi.item.service.IItemService;

/**
 * itemService业务层处理
 * 
 * @author group14
 * @date 2023-10-31
 */
@Service
public class ItemServiceImpl implements IItemService 
{
    @Autowired
    private ItemMapper itemMapper;

    /**
     * 查询item
     * 
     * @param itemId item主键
     * @return item
     */
    @Override
    public Item selectItemByItemId(Long itemId)
    {
        return itemMapper.selectItemByItemId(itemId);
    }

    /**
     * 查询item列表
     * 
     * @param item item
     * @return item
     */
    @Override
    public List<Item> selectItemList(Item item)
    {
        return itemMapper.selectItemList(item);
    }

    /**
     * 新增item
     * 
     * @param item item
     * @return 结果
     */
    @Override
    public int insertItem(Item item)
    {
        return itemMapper.insertItem(item);
    }

    /**
     * 修改item
     * 
     * @param item item
     * @return 结果
     */
    @Override
    public int updateItem(Item item)
    {
        return itemMapper.updateItem(item);
    }

    /**
     * 批量删除item
     * 
     * @param itemIds 需要删除的item主键
     * @return 结果
     */
    @Override
    public int deleteItemByItemIds(Long[] itemIds)
    {
        return itemMapper.deleteItemByItemIds(itemIds);
    }

    /**
     * 删除item信息
     * 
     * @param itemId item主键
     * @return 结果
     */
    @Override
    public int deleteItemByItemId(Long itemId)
    {
        return itemMapper.deleteItemByItemId(itemId);
    }
}
