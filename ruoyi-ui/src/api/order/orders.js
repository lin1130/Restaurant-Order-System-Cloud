import request from '@/utils/request'

// 查询order列表
export function listOrders(query) {
  return request({
    url: '/order/orders/list',
    method: 'get',
    params: query
  })
}

// 查询order详细
export function getOrders(orderId) {
  return request({
    url: '/order/orders/' + orderId,
    method: 'get'
  })
}

// 新增order
export function addOrders(data) {
  return request({
    url: '/order/orders',
    method: 'post',
    data: data
  })
}

// 修改order
export function updateOrders(data) {
  return request({
    url: '/order/orders',
    method: 'put',
    data: data
  })
}

// 删除order
export function delOrders(orderId) {
  return request({
    url: '/order/orders/' + orderId,
    method: 'delete'
  })
}
