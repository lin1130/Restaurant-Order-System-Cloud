import request from '@/utils/request'

// 查询item列表
export function listItem(query) {
  return request({
    url: '/item/item/list',
    method: 'get',
    params: query
  })
}

// 查询item详细
export function getItem(itemId) {
  return request({
    url: '/item/item/' + itemId,
    method: 'get'
  })
}

// 新增item
export function addItem(data) {
  return request({
    url: '/item/item',
    method: 'post',
    data: data
  })
}

// 修改item
export function updateItem(data) {
  return request({
    url: '/item/item',
    method: 'put',
    data: data
  })
}

// 删除item
export function delItem(itemId) {
  return request({
    url: '/item/item/' + itemId,
    method: 'delete'
  })
}
