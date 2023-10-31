package com.ruoyi.item.mapper;

import java.util.List;
import com.ruoyi.item.domain.Item;

/**
 * itemMapper接口
 * 
 * @author group14
 * @date 2023-10-31
 */
public interface ItemMapper 
{
    /**
     * 查询item
     * 
     * @param itemId item主键
     * @return item
     */
    public Item selectItemByItemId(Long itemId);

    /**
     * 查询item列表
     * 
     * @param item item
     * @return item集合
     */
    public List<Item> selectItemList(Item item);

    /**
     * 新增item
     * 
     * @param item item
     * @return 结果
     */
    public int insertItem(Item item);

    /**
     * 修改item
     * 
     * @param item item
     * @return 结果
     */
    public int updateItem(Item item);

    /**
     * 删除item
     * 
     * @param itemId item主键
     * @return 结果
     */
    public int deleteItemByItemId(Long itemId);

    /**
     * 批量删除item
     * 
     * @param itemIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteItemByItemIds(Long[] itemIds);
}
